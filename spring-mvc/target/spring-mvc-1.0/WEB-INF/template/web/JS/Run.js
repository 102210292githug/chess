	var isClick = false, isQuanDo = false;
	var Location = "00";
	var PointDo = 0, PointDen = 0;
	
	function KhoiTao(){
		VeBanCoTrangDen();
		DatCo();
		document.getElementById("PointCoDo").innerHTML = "Point: 0" ;
		document.getElementById("PointCoDen").innerHTML = "Point: 0";
		PointDo = 0;
		PointDen = 0;
		isClick = false;
		isQuanDo = false;
	}

	// Cờ đỏ đi trước
	function Click(id){
		var X = id.charAt(0);
		var Y = id.charAt(1);
		isClick = !isClick;
		if(isClick){
			if(isCoDo(X, Y) != isQuanDo){
			isClick = !isClick;
			return;
			}	
		}
		if(isClick){
			var Name = GetName(id);
			Name = Name.substring(0, Name.indexOf('_'));
			Location = id;
			
			// Kiểm tra này là quân cờ nào để xác định đường đi
			switch(Name){
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
				
					// Không click vào ổ chức quân cờ nào
					isClick = !isClick;
				break;
			}
		 }else{
			 var Name = GetName(id);
			 Name = Name.substring(0, Name.indexOf('_'));
			 
			 if(DiChuyen(Location, id)){
				 if(isQuanDo){
					 PointDo += GetDiem(Name);
					 if(isChieuVua(Name) || PointDo == 880){
					 	alert("QUÂN ĐỎ ĐÃ CHIẾN THẮNG");
						KhoiTao();
					 }
					 document.getElementById("PointCoDo").innerHTML = "Point: " + PointDo;
				 }else{
					 PointDen += GetDiem(Name);
					 if(isChieuVua(Name) || PointDen == 880){
					 	alert("QUÂN ĐEN ĐÃ CHIẾN THẮNG");
						KhoiTao();
					 }
					 document.getElementById("PointCoDen").innerHTML = "Point: " + PointDen;
				 }
				isQuanDo = !isQuanDo; 
				LuotDi();
			 }
			 VeBanCoTrangDen();
		 }
	}
