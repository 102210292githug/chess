var imported = document.createElement('script');
imported.src = 'template/web/JS/Sources.js';
document.head.appendChild(imported);

//
function VeBanCoTrangDen(){
	for(var i = 1; i < 9; i++){
		for(var j = 1; j < 9; j++){
			document.getElementById(i.toString() + j.toString()).style.backgroundColor = "#ffc69e";
			if(i % 2 != 0 ){
				if(j % 2 == 0)
					document.getElementById(i.toString() + j.toString()).style.backgroundColor = "#b06f4e";
			}else{
				if(j % 2 != 0)
					document.getElementById(i.toString() + j.toString()).style.backgroundColor = "#b06f4e";
			}
		}
	}
}
// Đặt các quân cờ
function DatCo(){
	for( i = 1; i < 9; i++){
		for(j = 1; j < 9; j++){
			document.getElementById("i" + i.toString() + j.toString()).src = "template/web/QuanCo/Rong.png";
		}
	}
	
	// Quân trắng
	for(var i = 1; i < 9; i++){
		document.getElementById("i7" + i).src = CoDen.Tot;
	}
	document.getElementById("i81").src = CoDen.Xe;
	document.getElementById("i82").src = CoDen.Ma;
	document.getElementById("i83").src = CoDen.Tuong;
	document.getElementById("i84").src = CoDen.Hau;
	document.getElementById("i85").src = CoDen.Vua;
	document.getElementById("i86").src = CoDen.Tuong;
	document.getElementById("i87").src = CoDen.Ma;
	document.getElementById("i88").src = CoDen.Xe;
	
	// Quân đen
	for(var i = 1; i < 9; i++){
		document.getElementById("i2" + i).src = CoDo.Tot;
	}
	document.getElementById("i11").src = CoDo.Xe;
	document.getElementById("i12").src = CoDo.Ma;
	document.getElementById("i13").src = CoDo.Tuong;
	document.getElementById("i14").src = CoDo.Vua;
	document.getElementById("i15").src = CoDo.Hau;
	document.getElementById("i16").src = CoDo.Tuong;
	document.getElementById("i17").src = CoDo.Ma;
	document.getElementById("i18").src = CoDo.Xe;
}