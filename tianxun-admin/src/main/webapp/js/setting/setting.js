//var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));

var elem = document.querySelector('.js-switch');
var elem_t = document.querySelector('.js-switch_t');
var switchery,switchery_t;
$(document).ready(function() {
	switchery = new Switchery(elem, { size: 'small' });
	switchery_t = new Switchery(elem_t, { size: 'small' });
 });


var clickCheckbox = document.querySelector('.js-check-click');
var clickCheckbox_t = document.querySelector('.js-check-click_t');
clickCheckbox.onchange = function() {
	switchery.disable();
	var id = this.id;
	bootbox.confirm("确定修改当前支付设置吗?", function(result) {
		if(result){
       		SettingCheckboxClick(id)
       	}else{
       		switchery.enable();
       		location.reload();
       	}
        $(this).closeDialog(modal); 
    });
};
clickCheckbox_t.onchange = function() {
	switchery_t.disable();
	var id = this.id;
	bootbox.confirm("确定修改当前支付设置吗?", function(result) {
		if(result){
       		SettingCheckboxClick(id)
       	}else{
       		switchery_t.enable();
       		location.reload();
       	}
        $(this).closeDialog(modal); 
    });
};
function SettingCheckboxClick(id){
 	 var flag = $('#'+id+'').is(':checked');
 	 var key_value = 0;
 	 if(flag){
 		key_value = 1
 	 }
 	 var json = "{\"keyName\":\""+id+"\",\"keyValue\":\""+key_value+"\"}";
     $.ajax({
     	 type:"post",
         contentType:"application/json; charset=UTF-8",
         url: ctx + "/setting/update",
         data:json,
         dataType:"json",
         success: function(msg){
        	 if(id == "offlinePay"){
    	 		switchery.enable(); 
    	 	 }else if(id == "askForOfflinePay"){
    	 		switchery_t.enable();
    	 	 }
        	 location.reload();
         },
         error: function (msg) {
        	$('#'+id+'').attr('disabled',false);
         	bootbox.alert("服务器偷了个小懒,修改PAD基础设置失败了！");
         }
     });
}