/**
 * 提交回复
 */
function post() {
    let questionId = $("#question_id").val();
    let content = $("#comment-content").val();

    comment2Target(questionId, 1, content);
}

function comment2Target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                if (response.code === 2003) {
                    let isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.160de4e8641db070&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    } else {

                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

function comment(e) {
    let commentId = e.getAttribute("data-id");

    let content = $("#input-" + commentId).val();
    console.log(content);
    comment2Target(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    let id = e.getAttribute("data-id");
    let comments = $("#comment-" + id);
    if (comments.hasClass("in")) {
        comments.removeClass("in");
        e.classList.remove("active");
    } else {

        let commentContainer = $("#comment-" + id);
        if(commentContainer.children().length!==1){
            comments.addClass("in");
            e.classList.add("active");
        }else{
            $.getJSON("/comment/" + id, function (data) {
                console.log(data);
                let commentContainer = $("#comment-" + id);
                $.each(data.data.reverse(), function (index, comment) {
                    console.log(comment);
                    let c = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 media-border",
                        html : "<div class=\"media media-border\">\n" +
                            "                                        <div class=\"media-left\">\n" +
                            "                                            <a href=\"#\">\n" +
                            "                                                <img class=\"media-object img-rounded \" style=\"width: 40px;height: 40px\"\n" +
                            "                                                     src=\""+comment.user.avatarUrl+"\"\n" +
                            "                                                     alt=\"头像\">\n" +
                            "                                            </a>\n" +
                            "                                        </div>\n" +
                            "                                        <div class=\"media-body\">\n" +
                            "                                            <h5 class=\"media-heading\" style=\"line-height: 40px\">\n" +
                            "                                                <span th:text=\"${comment.user.name}\">"+comment.user.name+"</span>\n" +
                            "                                            </h5>\n" +
                            "                                            <div th:text=\"${comment.content}\">"+comment.content+"</div>\n" +
                            "                                            <div class=\"icon-menu\">\n" +
                            "                                                <span class=\"glyphicon glyphicon-thumbs-up icon\"></span>\n" +
                            "                                                <span class=\"glyphicon glyphicon-comment icon\"\n" +
                            "                                                      th:data-id=\"${comment.id}\"\n" +
                            "                                                      onclick=\"collapseComments(this)\"></span>\n" +
                            "                                                <span class=\"pull-right\"\n" +
                            "                                                      >"+new Date(comment.gmtModified).toLocaleDateString()+"</span>\n" +
                            "                                            </div>\n" +
                            "                                        </div>\n" +
                            "                                    </div>"
                    });
                    commentContainer.prepend(c);
                });
                comments.addClass("in");
                e.classList.add("active");
            });
        }


    }
}

/**
 * 选择标签
 * @param value
 */
function selectTeg(e) {
    let value = e.getAttribute("data-tag");
    let previous = $("#tag").val();
    if ( previous.indexOf(value) === -1){
        if (previous){
            $("#tag").val(previous+','+value);
        }else {
            $("#tag").val(value);
        }
    }



}

/**
 * 展示标签
 */
function showSelectTag() {
    $("#select-tag").show();
}
