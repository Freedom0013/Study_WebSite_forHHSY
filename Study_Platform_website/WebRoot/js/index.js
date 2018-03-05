// JavaScript Document

function init() {
		$.ajax({
			url : "${pageContext.request.contextPath }/servlet/DepartmentServlet", //servlet文件的名称  
			type : "GET",
			success : function(msg) {
				var doms = eval("(" + msg + ")");
				var data = doms.root;
				var department_box_div = document.getElementById("department_box");
				if(data != null){
					for(var i in data){
						//表示遍历数组，而i表示的是数组的下标值，
						//data[i]表示获得第i个json对象即JSONObject
						//data[i]通过.字段名称即可获得指定字段的值
						
						data[i].department_name;
						data[i].department_addtime;
						data[i].department_caption;
						data[i].department_picture_id;
						
						var department_item_box = document.createElement("div");
						department_item_box.id = "department_item_box" + i;
						department_item_box.className = "box";
						department_box_div.appendChild(department_item_box);
						
						var image_a = document.createElement("a");
						var url = '${pageContext.request.contextPath }/servlet/ProfessionalServlet?department_id=' + data[i].department_id;
						image_a.setAttribute('href',url);
						image_a.setAttribute('target','_blank');
						department_item_box.appendChild(image_a);
						
						var department_img = document.createElement("img");
						
						
			    	}
				}
			},error : function() {
				alert("院系数据获取失败!请稍后重试!");
			}
		});
	}