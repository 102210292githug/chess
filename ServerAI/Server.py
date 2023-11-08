import socket

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
                if " and " in data:
                    player1, player2 = data.split(" and ")

                    if player2.strip() == "computer":
                        room_id = self.room_manager.create_player_vs_computer_room(player1.strip())
                        response = room_id
                    else:
                        room_id = self.room_manager.create_player_vs_player_room(player1.strip(), player2.strip())
                        response = room_id
                    print("PVC: " + str(room_manager.list_pvc_rooms()))
                    print("PVP: " + str(room_manager.list_pvp_rooms()))

                elif " move: " in data:
                    room_id, move_data = data.split(" move: ")
                    move = move_data.strip()

                    room_pvp = self.room_manager.get_pvp_room(room_id)
                    room_pvc = self.room_manager.get_pvc_room(room_id)

                    if room_pvp:
                        room_pvp.make_move(move)
                        legal_moves = room_pvp.get_legal_moves()
                        response = str(legal_moves)
                    elif room_pvc:
                        room_pvc.make_move(move)
                        computer_move = room_pvc.computer_move()
                        legal_moves = room_pvc.get_legal_moves()
                        response = f"Computer moved: {computer_move}, Legal moves: {legal_moves}"
                        # print(response)
                    else:
                        response = "Invalid room ID"
                else:
                    response = "Invalid command"

                # Send the response back to the server
                response += '\n'
                print(response, end="")
                client_socket.sendall(response.encode())


room_manager = RoomManager()
client = ChessClient("localhost", 8888, room_manager)
client.start()
