/**
 * 
 */

function completeTodo(todoId) {
	$.ajax({
		url: '/api/todos/' + todoId + '/complete'
		, type: 'POST'
		, headers: {
			'X-CSRF-TOKEN': csrfToken
		},
		success: function () {
			$('#btn-' + todoId).hide();
			$('#status-' + todoId).text('✔');
		},
		error: function () {
			alert('完了処理に失敗しました');
		}
	});
}

// CSRFトークンをmetaタグから取得してグローバル変数に保存
let csrfToken = '';
$(function () {
    csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr) {
        xhr.setRequestHeader(csrfHeader, csrfToken);
    });
});
