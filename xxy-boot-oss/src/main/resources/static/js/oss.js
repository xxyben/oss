oss = {
	api_get: function(uri, params, callback) {
        var index = layer.load(1, {
            shade : [ 0.1, '#fff' ]
        });
        $.ajax({
            url : uri,
            data : JSON.stringify(params),
            type : 'GET',
            cache : false,
            dataType : 'json',
            contentType : "application/json; charset=utf-8",
            success : function(data) {
                layer.close(index);
                callback(data);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                layer.close(index);
                if ("undefined" == typeof (XMLHttpRequest.responseJSON)) {
                    layer.alert("System error, please try again later.");
                    return;
                }

                if (40100 == XMLHttpRequest.responseJSON.code) {
                    location.href = "/";
                } else {
                    layer.alert(XMLHttpRequest.responseJSON.message);
                    api_invoke("/config/detail", {}, function(data) {
                        clientList = data.data;
                    });
                }
            }
        });
    },
    api_post: function(uri, params, callback) {
        var index = layer.load(1, {
            shade : [ 0.1, '#fff' ]
        });
        $.ajax({
            url : uri,
            data : JSON.stringify(params),
            type : 'POST',
            cache : false,
            dataType : 'json',
            contentType : "application/json; charset=utf-8",
            success : function(data) {
                layer.close(index);
                callback(data);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                layer.close(index);
                if ("undefined" == typeof (XMLHttpRequest.responseJSON)) {
                    layer.alert("System error, please try again later.");
                    return;
                }

                if (40100 == XMLHttpRequest.responseJSON.code) {
                    location.href = "/";
                } else {
                    layer.alert(XMLHttpRequest.responseJSON.message);
                    api_invoke("/config/detail", {}, function(data) {
                        clientList = data.data;
                    });
                }
            }
        });
    },	
	formatDateTime: function(timeStamp) { 
	    var date = new Date();
	    if(timeStamp<10000000000){
	    	date.setTime(timeStamp * 1000);
	    }
	    var y = date.getFullYear();    
	    var m = date.getMonth() + 1;    
	    m = m < 10 ? ('0' + m) : m;    
	    var d = date.getDate();    
	    d = d < 10 ? ('0' + d) : d;    
	    var h = date.getHours();  
	    h = h < 10 ? ('0' + h) : h;  
	    var minute = date.getMinutes();  
	    var second = date.getSeconds();  
	    minute = minute < 10 ? ('0' + minute) : minute;    
	    second = second < 10 ? ('0' + second) : second;   
	    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
	}
};