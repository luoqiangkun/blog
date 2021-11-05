;$(function (){
    if($("#table").length){
        let table = new AdminTable('#table',{
            url:"/admin/article-category/list",
            columns: [
                {
                    field : 'categoryId',
                    title : '分类ID',
                    align : 'center',
                    valign : 'middle',
                    width: 80,
                },
                {
                    field : 'categoryName',
                    title : '分类名称',
                    align : 'center',
                    valign : 'middle'
                },
                {
                    field : 'categorySort',
                    title : '分类排序',
                    align : 'center',
                    valign : 'middle'
                },
                {
                    field : 'categoryEnabled',
                    title : '分类状态',
                    align : 'center',
                    valign : 'middle',
                    formatter:function (val, row, index){
                        return '<div class="custom-control custom-switch"><input type="checkbox" class="custom-control-input" name="categoryEnabled" checked><label class="custom-control-label" for="categoryEnabled"></label></div>';
                    },
                },
                {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    formatter:function (val, row, index){
                      return '<div class="operating" data-id="'+row.categoryId+'"><a href="/admin/article-category/update?id='+row.categoryId+'" class="btn btn-primary btn-flat btn-sm update">编辑</a><a href="javascript:;" class="btn btn-danger btn-flat btn-sm delete">删除</a></div>';
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
                        url: "/admin/article-category/delete?id=" + id,
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
        $('#categoryEnabled').on('change', function (e) {
            let value = e.target.checked ? 1 : 0;
            $("input[name='categoryEnabled']").val(value);
        });
        $.validator.setDefaults({
            submitHandler: function () {
                let data = $('#form').serializeJSON();
                let url = data.categoryId ? '/admin/article-category/update' : '/admin/article-category/create';
                $.ajax({
                    type: "POST",
                    url: url,
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType: 'application/json;charset=UTF-8',
                    success: function(data){
                        if( data.status === 200 ){
                            location.href = "/admin/article-category/index";
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
                element.next().append(error);
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

