var imported = document.createElement('script');
imported.src = 'template/web/JS/Sources.js';
document.head.appendChild(imported);

//
function VeBanCoTrangDen() {
	for (var j = 1; j <= 8; j++) {
		for (var i = 'a'.charCodeAt(0); i <= 'h'.charCodeAt(0); i++) {
			var kytu = String.fromCharCode(i);
			var id = kytu + j;
			var laOTrang = (i + j) % 2 == 1;
			document.getElementById(id).style.backgroundColor = laOTrang ? Mau.Trang : Mau.Den;
		}
	}
}

function DatCo() {
    for (var i = 1; i < 9; i++) {
        for (var j = 1; j < 9; j++) {
            var cellId = "i" + String.fromCharCode('a'.charCodeAt(0) + j - 1) + i;
            document.getElementById(cellId).src = "template/web/QuanCo/Rong.png";
        }
    }

    // Đặt quân cờ đen
    for (var j = 1; j < 9; j++) {
        var pawnId = "i" + String.fromCharCode('a'.charCodeAt(0) + j - 1) + "7";
        document.getElementById(pawnId).src = CoDen.Tot;
    }

    var blackPieces = [CoDen.Xe, CoDen.Ma, CoDen.Tuong, CoDen.Hau, CoDen.Vua, CoDen.Tuong, CoDen.Ma, CoDen.Xe];
    for (var j = 1; j < 9; j++) {
        var pieceId = "i" + String.fromCharCode('a'.charCodeAt(0) + j - 1) + "8";
        document.getElementById(pieceId).src = blackPieces[j - 1];
    }

    // Đặt quân cờ trắng
    for (var j = 1; j < 9; j++) {
        var pawnId = "i" + String.fromCharCode('a'.charCodeAt(0) + j - 1) + "2";
        document.getElementById(pawnId).src = CoTrang.Tot;
    }

    var whitePieces = [CoTrang.Xe, CoTrang.Ma, CoTrang.Tuong, CoTrang.Hau, CoTrang.Vua, CoTrang.Tuong, CoTrang.Ma, CoTrang.Xe];
    for (var j = 1; j < 9; j++) {
        var pieceId = "i" + String.fromCharCode('a'.charCodeAt(0) + j - 1) + "1";
        document.getElementById(pieceId).src = whitePieces[j - 1];
    }
}