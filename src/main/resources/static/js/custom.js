function getJpa(target, token, fn) {
    var res;
    $.ajax({
        url : location.origin + '/api/v1/jpa/' + target,
        type : 'get',
        async : false,
        beforeSend : function(xhr){
            xhr.setRequestHeader("Authorization", "Bearer "+token);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader("Accept", "application/json");
        },
        dataType : 'json',
        statusCode : {
            200 : fn,
            400 : function (response) {
                alert('error - ' + response);
            }
        }
    });
}

function postJpa(target, token, data, success) {
    $.ajax({
        url : location.origin + '/api/v1/jpa/' + target,
        type : 'post',
        beforeSend : function(xhr){
            xhr.setRequestHeader("Authorization", "Bearer "+token);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader("Accept", "application/json");
        },
        dataType : 'json',
        data : JSON.stringify(data),
        statusCode : {
            201 : success,
            400 : function (response) {
                alert('error - ' + response);
            }
        }
    });
}