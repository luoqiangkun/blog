function AdminTable(element,options,handler){
    this.element = $(element);
    this.options = options;
    this.table = null;
    this.init();
}
AdminTable.prototype.init = function(){
    let options = $.extend({},{
        url: '',
        columns:null,
        classes:'table table-hover',
        sidePagination: 'server',
        method: 'get', //请求方法
        dataType:"json",
        contentType: "application/x-www-form-urlencoded",
        toolbar:'#toolbar',
        toolbarAlign: 'left',
        search: false, //是否启用快速搜索
        cache: false,
        pageNumber:1,
        pageSize: 10,
        pageList: [10, 20, 50, 100],
        pagination: true,
        clickToSelect: false, //是否启用点击选中
        singleSelect: false, //是否启用单选
        locale: 'zh-CN',
        sortName: 'id',
        sortOrder: 'desc',
        cardView: false, //卡片视图
        escape: true, //是否对内容进行转义
        totalField: 'total',
        dataField: 'list',
        uniqueId:'id',
        idField:'id',
        showPaginationSwitch:false,
        showRefresh:false,
        showToggle:false,
        showFullscreen:false,
        showColumns:false,
        showToolbar:false,
        responseHandler: function (res){
            return res.data;
        },
        queryParamsType:'',
        queryParams: function(params){
            return params;
        }
    }, this.options);
    this.table = this.element.bootstrapTable(options);
}

AdminTable.prototype.getTable = function (){
    return this.table;
}
AdminTable.prototype.initHandle = function (handler,actions){
    if( !actions ){
        actions = ['update','delete'];
    }
    for( let i in actions ){
        if( 'update' === actions[i]){
            this.element.on('click','.update',function (){
                let id = $(this).parent('.operating');
                handler.update(id);
            })
        }
        if( 'delete' === actions[i]){
            this.element.on('click','.delete',function (){
                let id = $(this).parent('.operating');
                handler.delete(id);
            })
        }
        if( 'create' === actions[i]){
            this.element.on('click','.delete',function (){
                let id = $(this).parent('.operating');
                handler.create(id);
            })
        }
    }
}

function Handler(url,options){
    this.baseUrl = url;
    this.options = options;
    this.init();
}
Handler.prototype.init = function (){
    this.createUrl = this.baseUrl + '/create';
    this.updateUrl = this.baseUrl + '/update';
    this.deleteUrl = this.baseUrl + '/delete';
}
Handler.prototype.create = function (id){
    this.action = 'create';
    $.ajax({
        type: "POST",
        url: this.createUrl,
        data: JSON.stringify({id:id}),
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: (data) => {
            if( data.status === 200 ){
                this.callback()
            }else{
                alert( data.message );
            }
        }
    });
}
Handler.prototype.update = function (id){
    this.action = 'update';
    $.ajax({
        type: "POST",
        url: this.updateUrl,
        data: JSON.stringify({id:id}),
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: (data) => {
            if( data.status === 200 ){
                this.callback()
            }else{
                alert( data.message );
            }
        }
    });
}
Handler.prototype.delete = function (id){
    this.action = 'delete';
    $.ajax({
        type: "POST",
        url: this.deleteUrl,
        data: JSON.stringify({id:id}),
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: (data) => {
            if( data.status === 200 ){
                this.callback()
            }else{
                alert( data.message );
            }
        }
    });
}
Handler.prototype.callback = function (){
    if(this.options.callback && typeof this.options.callback === 'function'){
        this.options.callback();
    }
}
Handler.prototype.setUrl = function (url,action){
    if( 'create' === action ){
        this.createUrl = url;
    } else if( 'update' === action ){
        this.updateUrl = url;
    } else if( 'delete' === action ){
        this.deleteUrl = url;
    }
}
function AdminForm(element,options){
    this.element = $(element);
    this.options = options;
}
