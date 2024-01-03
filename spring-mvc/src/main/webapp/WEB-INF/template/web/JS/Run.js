var isClick = false, isWhite = false, Done = true, EndGame = false;
var Location = "00";
var PointDo = 0, PointDen = 0;
function KhoiTao() {
	//VeBanCoTrangDen();
	//DatCo();
//	isClick = false;
//
//	// k dung
//	PointDo = 0;
//	PointDen = 0;
//	document.getElementById("PointCoDo").innerHTML = "Point: 0";
//	document.getElementById("PointCoDen").innerHTML = "Point: 0";
}
function WhiteOrBlack(isW){
	Done = isW;
	isWhite = !isW;
}

// Cờ đỏ đi trước
function Nextturn() {
	Done = true;
}
function Click(id) {
	console.log(Done);
	if (!Done)
		return;
	var X = id.charAt(0);
	var Y = id.charAt(1);
	isClick = !isClick;
	if (isClick) {
		if (isCoDo(X, Y) != isWhite) {
			isClick = !isClick;
			return;
		}
	}
	if (isClick) {
		var Name = GetName(id);
		Name = Name.substring(0, Name.indexOf('_'));
		Location = id;

		// Kiểm tra này là quân cờ nào để xác định đường đi
		switch (Name) {
		case 'Xe':
			Xe(id);
			break;

		case 'Ma':
			Ma(id);
			break;

		case 'Tuong':
			Tuong(id);
			break;

		case 'Hau':
			Hau(id);
			break;

		case 'Vua':
			Vua(id);
			break;

		case 'Tot':
			Tot(id);
			break;

		default:

			// Không click vào ổ chứa quân cờ nào
			isClick = !isClick;
			break;
		}
	} else {
		var Name = GetName(id);
		Name = Name.substring(0, Name.indexOf('_'));

		if (DiChuyen(Location, id)) {
			if (isWhite) {
				PointDo += GetDiem(Name);
				if (isChieuVua(Name) || PointDo == 880) {
					alert("YOU WIN");
					sendMove(Location, id);
					endGame();
					KhoiTao();
				}
				document.getElementById("PointCoDo").innerHTML = "Point: "
						+ PointDo;
			} else {
				PointDen += GetDiem(Name);
				if (isChieuVua(Name) || PointDen == 880) {
					sendMove(Location, id);
					endGame();
					alert("YOU WIN");
					KhoiTao();
				}
				document.getElementById("PointCoDen").innerHTML = "Point: "
						+ PointDen;
			}
			Done = false;
			sendMove(Location, id);
		}
		VeBanCoTrangDen();
	}
}