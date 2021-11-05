;$(function (){
    if($("#table").length){
        let table = new AdminTable('#table',{
            url:"/admin/article/list",
            columns: [
                {
                    field : 'articleId',
                    title : '文章ID',
                    align : 'center',
                    valign : 'middle',
                    width: 80,
                },
                {
                    field : 'articleTitle',
                    title : '文章标题',
                    align : 'center',
                    valign : 'middle'
                },
                {
                    field : 'articleDesc',
                    title : '文章描述',
                    align : 'center',
                    valign : 'middle'
                },
                {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    formatter:function (val, row, index){
                      return '<div class="operating" data-id="'+row.articleId+'"><a href="/admin/article/update?articleId='+row.articleId+'" class="btn btn-primary btn-flat btn-sm update">编辑</a><a href="javascript:;" class="btn btn-danger btn-flat btn-sm delete">删除</a></div>';
                    },
                    width: 150,
                    visible:true
                }
            ]
        });

        $('#table').on('click','.delete',function (){
            let id = $(this).parent().data('id');
            swal.fire({
                title: "Are you sure?",
                text: "Once deleted, you will not be able to recover this imaginary file!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
            .then((willDelete) => {
                if (willDelete) {
                    $.ajax({
                        type: "POST",
                        url: "/admin/article/delete?articleId=" + id,
                        dataType: "json",
                        contentType: 'application/json;charset=UTF-8',
                        success: function(data){
                            if( data.status === 200 ){
                                location.reload();
                            }else{
                                alert("操作失败");
                            }
                        }
                    });
                } else {
                    swal("Your imaginary file is safe!");
                }
            });

        });
    }
    if($('#form').length){
        $('#articleContent').summernote({
            height: 500,
            tabsize: 2,
            lang: 'zh-CN'
        });
        $.validator.setDefaults({
            submitHandler: function () {
                let data = $('#form').serializeJSON();
                let url = data.articleId ? '/admin/article/update' : '/admin/article/create';
                $.ajax({
                    type: "POST",
                    url: url,
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType: 'application/json;charset=UTF-8',
                    success: function(data){
                        if( data.status === 200 ){
                            location.href = "/admin/article/index";
                        }else{
                            alert("操作失败");
                        }
                    }
                });
            }
        });
        $('#form').validate({
            rules: {
                articleTitle: {
                    required: true
                }
            },
            messages: {
                articleTitle: {
                    name: "请填写新闻标题"
                }
            },
            errorElement: 'span',
            errorPlacement: function (error, element) {
                error.addClass('invalid-feedback');
                element.closest('.form-group').append(error);
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });
    }
})

