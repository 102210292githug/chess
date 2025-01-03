import json
import socket
import re

from ai.analyzer import analyze_game
from rooms.RoomManager import RoomManager


class ChessClient:
    def __init__(self, host, port, room_manager):
        self.host = host
        self.port = port
        self.room_manager = room_manager

    def start(self):
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
            client_socket.connect((self.host, self.port))

            while True:
                # Receive message from server
                data = client_socket.recv(1024).decode()
                print(f"Received: {data}")
                value = data.split()
                if value[0] == "createGame":
                    if value[1] == 'mode=0':
                        self.room_manager.create_player_vs_computer_room(value[2], value[4])
                    else:
                        self.room_manager.create_player_vs_player_room(value[2], value[3], value[4])
                    print("PVC: " + str(room_manager.list_pvc_rooms()))
                    print("PVP: " + str(room_manager.list_pvp_rooms()))
                    response = "Created"
                elif value[0] == "move":
                    room_id = value[1].split('=')[-1]
                    move_data = value[2].split('=')[-1]
                    move = move_data.strip()
                    room_pvp = self.room_manager.get_pvp_room(room_id)
                    room_pvc = self.room_manager.get_pvc_room(room_id)

                    if room_pvp:
                        room_pvp.make_move(move)
                        legal_moves = room_pvp.get_legal_moves()
                        response = str(legal_moves)
                        response = f"Computer moved: {move}, Legal moves: {legal_moves}"
                    elif room_pvc:
                        room_pvc.make_move(move)
                        computer_move = room_pvc.computer_move()
                        legal_moves = room_pvc.get_legal_moves()
                        response = f"Computer moved: {computer_move}, Legal moves: {legal_moves}"
                        # print(response)
                    else:
                        response = "Invalid room ID"
                elif value[0] == "get":
                    response = room_manager.get_board_string_for_room(value[1])
                elif value[0] == "Check":
                    room_pvp = self.room_manager.get_pvp_room(room_id)
                    room_pvc = self.room_manager.get_pvc_room(room_id)
                    if room_pvc or room_pvp:
                        response = "have"
                    else:
                        response = "not-have"
                elif value[0] == "Legal":
                    room_id = value[1]
                    room_pvp = self.room_manager.get_pvp_room(room_id)
                    room_pvc = self.room_manager.get_pvc_room(room_id)
                    if self.room_manager.list_pvp_rooms().count(value[1]):
                        legal_moves = room_pvp.get_legal_moves()
                        response = str(legal_moves)
                    if self.room_manager.list_pvc_rooms().count(value[1]):
                        legal_moves = room_pvc.get_legal_moves()
                        response = str(legal_moves)
                elif value[0] == "Add":
                    # Tách phần không phải nước đi
                    non_move_parts = data.split()[:5]

                    # Tách các nước đi sử dụng biểu thức chính quy
                    moves_str = re.search(r'\[(.*?)\]', data).group(1)
                    moves = moves_str.split(', ')
                    mode = non_move_parts[1].split('=')[-1]
                    pid1 = non_move_parts[2]
                    pid2 = non_move_parts[3]
                    room_id = non_move_parts[4]
                    if mode == '0':
                        self.room_manager.create_player_vs_computer_room(pid1, room_id)
                        room_pvc = self.room_manager.get_pvc_room(room_id)
                        if moves[-1] != '':
                            for move in moves:
                                move = move.strip()
                                room_pvc.make_move(move)
                    else:
                        self.room_manager.create_player_vs_player_room(pid1, pid2, room_id)
                        room_pvp = self.room_manager.get_pvc_room(room_id)
                        if moves[-1] != '':
                            for move in moves:
                                move = move.strip()
                                room_pvp.make_move(move)
                    response = "Done!"
                elif value[0] == "Analysis":
                    game_moves = re.findall(r'\b([a-h][1-8][a-h][1-8])\b', data)
                    response_str = analyze_game(game_moves)
                    response = json.dumps(response_str)
                else:
                    response = "Invalid command"

                # Send the response back to the server
                response += '\n'
                print(response, end="")
                client_socket.sendall(response.encode())


room_manager = RoomManager()
client = ChessClient("localhost", 8888, room_manager)
client.start()
