function gfn_isNull(str){
	if(str == null) return true;
	if(str == "NaN") return true;
	if(new String(str).valueOf() == "undefined") return true;
	var chkStr = new String(str);
	if(chkStr.valueOf() == "undefined") return true;
	if(chkStr == null) return true;
	if(chkStr.toString().length == 0)return true;
	return false;
}
/*ComSubmit 객체는 객체가 생성될 때, 폼의 아이디가 인자값으로 들어오면 그 폼을 전송하고,파라미터가 없으면 숨겨둔 폼을 이용하여 데이터를 전송하도록 구현하였다. 
    따라서 전송할 데이터가 있는 frm이라는 id를 가진 form을 이용하도록 id를 넘겨주었다.
*/
function ComSubmit(opt_formId){
	this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
	this.url ="";
	
	if(this.formId =="commonForm"){
		$("#commonForm")[0].reset();
	}
	
	this.setUrl = function setUrl(url){
		this.url = url;
	};
	
	//이 함수가 실행될때 인자값으로 $(this)가 넘겨지는것을 볼 수 있다. 이는 jQuery 객체를 뜻하는데, 여기서는 게시글 제목인  <a> 태그를 의미한다
	this.addParam = function addParam(key, value){
		$("#" + this.formId).append($("<input type='hidden' name='" + key + "' id='" + key + "'" +
				" value='" + value +"'>"));
	};
	
	this.submit = function submit(){
		var frm = $("#" + this.formId)[0];
		frm.action = this.url;
		frm.method = "post";
		frm.submit();
	};
}