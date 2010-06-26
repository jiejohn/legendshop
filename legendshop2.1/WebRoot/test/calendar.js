/**
 * Calendar
 * @param   beginYear           1990
 * @param   endYear             2010
 * @author xiaohongli
 */
 
document.writeln('<div id="__calendarPanel" style="position:absolute;visibility:hidden;z-index:9999;width:200px;height:40px;">');//200px; 185
document.writeln('<iframe name="__calendarIframe" id="__calendarIframe" width="100%" height="100%" scrolling="no" frameborder="0" style="margin:0px;"><\/iframe>');
document.writeln('<\/div>');

function Calendar(beginYear, endYear ) {
    var _date = new Date();
    var _year = _date.getFullYear();
    this.beginYear = beginYear || ( _year - 20 );
    this.endYear   = endYear   || ( _year + 10 );
    this.dateControl = null;
    this.form   = null;
    this.colors = {"bg_cur_day":"#00CC33","bg_over":"#EFEFEF","bg_out":"#FFCC00","bg_cur":'#FFff40'}//#00CC33
};

Calendar._language = 'en_us' ;
Calendar.initLanguage = function(language){
    var _language = language ;
    if(!_language){
         if (navigator.language)  
             _language = navigator.language;
         else if (navigator.browserLanguage)
             _language = navigator.browserLanguage;
    }

    for(var i=0 ;i<Calendar.supportLanguage.length ;i++){
        var o = Calendar.supportLanguage[i] ;
        if( o == _language.toLowerCase()){
            Calendar._language = o ;
            break ;
        }
    }
    
    Calendar.lang = {
        year: Calendar.res['year'][Calendar._language],
        month: Calendar.res['month'][Calendar._language],
        hour: Calendar.res['hour'][Calendar._language],
        min: Calendar.res['min'][Calendar._language],
        clear: Calendar.res['clear'][Calendar._language],
        today: Calendar.res['today'][Calendar._language],
        close: Calendar.res['close'][Calendar._language],
        confirm: Calendar.res['confirm'][Calendar._language],
        weeks: Calendar.res['weeks'][Calendar._language],
        titles: Calendar.res['titles'],
        width:Calendar.res['width'][Calendar._language]
    }
}
Calendar.supportLanguage = ['zh_cn','en_us','en_en','zh_tw'] ;
Calendar.res = {
    year: {
        'zh_cn':"\u5e74",
        'en_us':"",
        'en_en':"",
        'zh_tw':"\u5e74"
    },
    month: {
        'zh_cn':"\u6708",
        'en_us':"",
        'en_en':"",
        'zh_tw':"\u6708"
    },
    hour: {
        'zh_cn':'\u65f6',
        'en_us':"",
        'en_en':"",
        'zh_tw':'\u65f6'
    },
    min: {
        'zh_cn':'\u5206',
        'en_us':"",
        'en_en':"",
        'zh_tw':'\u5206'
    },
    clear: {
        'zh_cn':"\u6e05\u7a7a",
        'en_us':"Clear",
        'en_en':"Clear",
        'zh_tw':"\u6e05\u7a7a"
    },
    today: {
        'zh_cn':"\u4eca\u5929",
        'en_us':"Today",
        'en_en':"Today",
        'zh_tw':"\u4eca\u5929"
    },
    close: {
        'zh_cn':"\u5173\u95ed",
        'en_us':"Close",
        'en_en':"Close",
        'zh_tw':"\u95dc\u9589"
    },
    confirm:{
        'zh_cn':"\u786e\u5b9a",
        'en_us':"OK",
        'en_en':"OK",
        'zh_tw':"\u786e\u5b9a"
    },
    months: {
        'zh_cn':["1\u6708","2\u6708","3\u6708","4\u6708","5\u6708","6\u6708","7\u6708","8\u6708","9\u6708","10\u6708","11\u6708","12\u6708"],
        'en_us':["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"],
        'en_en':["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"],
        'zh_tw':["1\u6708","2\u6708","3\u6708","4\u6708","5\u6708","6\u6708","7\u6708","8\u6708","9\u6708","10\u6708","11\u6708","12\u6708"]
    },
    weeks: {
        'zh_cn':["\u65e5","\u4e00","\u4e8c","\u4e09","\u56db","\u4e94","\u516d"],
        'en_us':["Sun","Mon","Tur","Wed","Thu","Fri","Sat"],
        'en_en':["Sun","Mon","Tur","Wed","Thu","Fri","Sat"],
        'zh_tw':["\u65e5","\u4e00","\u4e8c","\u4e09","\u56db","\u4e94","\u516d"]
    },
    titles: ["","","",""]
    //titles:["\u4e0a\u4e00\u6708","\u4e0b\u4e00\u6708","\u4e0a\u4e00\u5e74","\u4e0b\u4e00\u5e74"]
};

Calendar.res.width = {
    'zh_cn':185,
    'en_us':205,
    'en_en':205,
    'zh_tw':185
}


var __on = true ;
var ___calendarCreate = false ;

 /*
        yyyy-MM-dd:194px;
        yyyy-MM:62px;
        dd:163px;
        hh:mm:ss :53px;
        yyyy-MM hh:mm:ss :84px;
        dd hh:mm:ss:186px;
 */
 Calendar.prototype.getHeight = function(){
    var ym = this.getFieldStyle("y,M","|")=="" ;
    var d =  this.getFieldStyle("d")=="" ;
    var hms = this.getFieldStyle("h,m","|")=="" ;
    if(ym&&d&&hms) return 219 ;
    if(ym&&d) return 196 ;
    if(ym&&hms) return 86 ;
    if(d&&hms) return 188 ;
    if(d) return 165 ;
    if(hms) return 55 ;
    if(ym) return 64 ;
 }

Calendar.prototype.content = function(){
    this.panel  = this.getElementById("__calendarPanel");
    this.iframe = window.frames["__calendarIframe"];
    if(___calendarCreate)return ;
    ___calendarCreate = true ;
    var __ci = window.frames['__calendarIframe'];
    __ci.document.writeln('<html>');// xmlns="http:\/\/www.w3.org\/1999\/xhtml"
    __ci.document.writeln('<head>');
    __ci.document.writeln('<meta http-equiv="Content-Type" content="text\/html; charset=UTF-8" \/>');
    __ci.document.writeln('<title><\/title>');
    __ci.document.writeln('<style type="text\/css">');
    __ci.document.writeln('<!--');
    __ci.document.writeln('body {font-size:11px;margin:0px;text-align:center;}');
    __ci.document.writeln('form {margin:0px;}');
    __ci.document.writeln('select {font-size:11px;background-color:#EFEFEF;}');
    __ci.document.writeln('th {font-size:12px;font-weight:normal;}');
    __ci.document.writeln('th.theader {font-weight:normal;background-color:#0080FF;color:#FFFFFF;width:24px;}');
    __ci.document.writeln('select.year {width:50px;}');
    __ci.document.writeln('select.month {width:38px;}');
    __ci.document.writeln('td {font-size:11px;text-align:center;color:#000000;}');
    __ci.document.writeln('td.sat {color:#0000FF;}');
    __ci.document.writeln('td.sun {color:#FF0000;}');
    __ci.document.writeln('td.normal {color:#000000;}');
    __ci.document.writeln('td.old {color:#999;}');
    var buttonStyle     ="height:20px;background-color: #FFF7E7;border:1px solid #78acff;font-size:12px;cursor:hand;FONT-SIZE: 9pt;font-family:\u00cc\u008e\u00cd\u00a5;";//\u00c2\u00b0\u00c2\u00b4\u00c5\u00a5\u00c2\u00b5\u00c3\u0083ss
    __ci.document.writeln('input.l {width:10;'+buttonStyle+'}');
    __ci.document.writeln('input.r {width:10;'+buttonStyle+'}');
    __ci.document.writeln('input.ll {width:15;'+buttonStyle+'}');
    __ci.document.writeln('input.rr {width:15;'+buttonStyle+'}');
    __ci.document.writeln('input.b {'+buttonStyle+'}');
    __ci.document.writeln('.hmClass th{font-size:11px;}');
    __ci.document.writeln('.hmClass select{font-size:11px;height:20px;}');
    __ci.document.writeln('-->');//hmClass
    __ci.document.writeln('<\/style>');
    __ci.document.writeln('<\/head>');
    __ci.document.writeln('<body>');
    __ci.document.writeln('<\/body>');
    __ci.document.writeln('<\/html>');
    __ci.document.close();
}

Calendar.prototype.show = function (dateControl,dateFormat,event) {//, popuControl
    this.dateFormat = dateFormat ;
    Calendar.stopBubble(event) ;
    this.content() ;

    document.getElementById("__calendarIframe").height = this.getHeight()+"px" ;
    this.panel.style.height =  this.getHeight()+"px" ;
    
    document.getElementById("__calendarIframe").width = Calendar.lang['width']+"px" ;
    this.panel.style.width =  Calendar.lang['width']+"px" ;
    
    if (this.panel.style.visibility == "visible") {
        this.panel.style.visibility = "hidden";
    }
    if (!dateControl){
        throw new Error("arguments[0] is necessary!");
    }

    this.dateControl = dateControl;
    popuControl =   dateControl;//, popuControl
    this.date2StringPattern = dateFormat||"yyyy-MM-dd" ;
    if (dateControl.value.length > 0){
        this.date  = new Date(dateControl.value.toDate(this.date2StringPattern));
    }else{
        this.date = new Date() ;
    }
    this.year  = this.date.getFullYear();
    this.month = this.date.getMonth();
    
    this.draw();
    this.bindYear();
    this.bindMonth();
    this.bindHour() ;
    this.bindMin() ;
    this.changeSelect();
    this.changeHourMin();
    this.bindData();

    var xy = this.getAbsPoint(popuControl);
    this.panel.style.left = xy.x + "px";
    this.panel.style.top = (xy.y + dateControl.offsetHeight) + "px";
    this.panel.style.visibility = "visible";
    
};

Calendar.prototype.hide = function() {
    if(!this.panel) return ;
    this.panel.style.visibility = "hidden";
};

Calendar.prototype.getFieldStyle = function(field,flag){// y M d h m s
    var fs = field.split(",");
    var dF = this.dateFormat ;
    var bool = false ;
    var count=0 ;
    for(var i=0 ;i<fs.length ;i++){
        var f = fs[i] ;
        if(dF.indexOf(f)!=-1){
            bool = true ;
            count++ ;
        }
    }
    var f = flag||"|" ;
    if(f=='&'){
        if(count == fs.length) return "" ;
    }else{
        if(bool) return "" ;
    }
    
    return "display:none;" ;
}

Calendar.prototype.draw = function(withHourMin) {
    var dF = this.dataFormat ;
    
    withHourMin = true ;
    calendar = this;
    
    var _cs = [];
    _cs[_cs.length] = "<table border=\"0\" cellspacing=\"0\" cellpadding=\"2\" style=\"margin:0px;padding:0px;\">\n "; 
    _cs[_cs.length] =   "<tr>\n ";
    _cs[_cs.length] =   "<td  bgcolor=\"#0080FF\">\n ";
    _cs[_cs.length] = '<form id="__calendarForm" name="__calendarForm" method="post">';
    _cs[_cs.length] = '<table id="__calendarTable"  border=\"0\" cellspacing=\"1\" cellpadding=\"2\" bgcolor=\"Silver\" style="table-layout:fixed">';

    var headStyle= "bgcolor=\"#C8E3FF\" height=22 onmouseover=\"style.backgroundColor=\'#FFD700\'\" onmouseout=\"style.backgroundColor=\'#C8E3FF\'\"" ;

    _cs[_cs.length] = ' <tr style="margin:0px;padding:0px;'+this.getFieldStyle("y,M")+'"><th colspan="7" height="30" bgcolor="#C8E3FF" style="margin:0px;padding:0px;">';
    _cs[_cs.length] = ' <table width="100%" style="margin:0px;padding:0px;table-layout:fixed;" border=0><tr>';//
    _cs[_cs.length] = '  <th style="margin:0px;padding:0px;" width=35px align="left">' ;
    _cs[_cs.length] = '<input class="ll" name="goPrevYearButton" style="'+this.getFieldStyle("y")+'" type="button" title="'+Calendar.lang['titles'][2]+'" id="goPrevYearButton" value="&lt;&lt;" \/>';
    _cs[_cs.length] = '  <input class="l" name="goPrevMonthButton" style="'+this.getFieldStyle("M")+'" title="'+Calendar.lang['titles'][0]+'" type="button" id="goPrevMonthButton" value="&lt;" \/><\/th>';
    _cs[_cs.length] = '  <th id="yssId" '+headStyle+' style=\"font-size:12px;cursor:hand;margin:0px;'+this.getFieldStyle("y")+'\"  width=52px><span id="yearSelectSapn">'+this.year+' \u5e74</span><\/th>';
    _cs[_cs.length] = '  <th id="ysId" '+headStyle+' style=\"font-size:12px;cursor:hand;margin:0px;display:none;\" width=52px><select class="year" name="yearSelect" id="yearSelect"><\/select><\/th>';
    _cs[_cs.length] = '  <th id="msId" '+headStyle+' style=\"font-size:12px;cursor:hand;margin:0px;display:none;\" width=40px><select class="month" name="monthSelect" id="monthSelect"><\/select><\/th>';
    _cs[_cs.length] = '  <th id="mssId" '+headStyle+' style=\"font-size:12px;cursor:hand;margin:0px;'+this.getFieldStyle("M")+'\"   width=40px><span id="monthSelectSapn">'+(this.month+1)+' \u6708</span><\/th>';
    _cs[_cs.length] = '  <th width=35px  style="margin:0px;padding:0px;" align="right"><input class="r" style="'+this.getFieldStyle("M")+'" name="goNextMonthButton" title="'+Calendar.lang['titles'][1]+'" type="button" id="goNextMonthButton" value="&gt;" \/>';
    _cs[_cs.length] = '  <input class="rr" style="'+this.getFieldStyle("y")+'" name="goNextYearButton" type="button" title="'+Calendar.lang['titles'][3]+'" id="goNextYearButton" value="&gt;&gt;" \/><\/th>';
    
    _cs[_cs.length] = ' <tr></table><\/th><\/tr>';
    
    _cs[_cs.length] = ' <tr style="'+this.getFieldStyle("d")+'">';
    for(var i = 0; i < 7; i++) {
        _cs[_cs.length] = '<th class="theader">';
        _cs[_cs.length] = Calendar.lang["weeks"][i];
        _cs[_cs.length] = '<\/th>'; 
    }
    _cs[_cs.length] = '<\/tr>';
    for(var i = 0; i < 6; i++){
        _cs[_cs.length] = '<tr align="center" style="'+this.getFieldStyle("d")+'">';
        for(var j = 0; j < 7; j++) {
            switch (j) {
                case 0: _cs[_cs.length] = '<td bgcolor="#ffffff" class="sun">&nbsp;<\/td>'; break;
                case 6: _cs[_cs.length] = '<td bgcolor="#ffffff" class="sat">&nbsp;<\/td>'; break;
                default:_cs[_cs.length] = '<td bgcolor="#ffffff" class="normal">&nbsp;<\/td>'; break;
            }
        }
        _cs[_cs.length] = '<\/tr>';
    }
    
    _cs[_cs.length] = ' <tr style="margin:0px;padding:0px;">';
    _cs[_cs.length] = '<th  colspan="7" height="24px"  bgcolor="#C8E3FF"  style="margin:0px;padding:0px;'+this.getFieldStyle("h,m")+'">';
    _cs[_cs.length] = '<table  style="margin:0px;padding:0px;" width="100%"  style="table-layout:fixed"><tr>';
    _cs[_cs.length] = '<th   '+headStyle+' style=\"font-size:12px;cursor:hand;margin:0px;padding:0px;'+this.getFieldStyle("h")+'\" id="hspanId"><\/th>';
    _cs[_cs.length] = '<th  '+headStyle+' style=\"font-size:12px;cursor:hand;margin:0px;padding:0px;display:none;\" id="hsId"><select id="hourSelect"  name="hourSelect"></select></th>';
    _cs[_cs.length] = '<th  style="font-size:11px;margin:0px;padding:0px;vertical-align:middle;'+this.getFieldStyle("m,h","&")+'"   align="center">\uff1a</th>';
    _cs[_cs.length] = '<th  '+headStyle+' style=\"font-size:12px;cursor:hand;margin:0px;padding:0px;display:none;\" id="minsId"><select id="minSelect"  name="minSelect"></select></th>';
    _cs[_cs.length] = '<th  '+headStyle+' style=\"font-size:12px;cursor:hand;margin:0px;padding:0px;'+this.getFieldStyle("m")+'\"  id="minspanId">20<\/th>';
    _cs[_cs.length] = '</tr></table>';
    _cs[_cs.length] = '<\/th>';
    _cs[_cs.length] =  "</tr> ";
    
    _cs[_cs.length] = ' <tr style="margin:0px;padding:0px;">';
    _cs[_cs.length] = '<th colspan="7" bgcolor="#C8E3FF" height="24px" style="margin:0px;padding:0px;">';
     if( window.attachEvent ) 
        _cs[_cs.length] = '<table width="100%" border=0><tr>';
     else
        _cs[_cs.length] = '<table width="100%" style="table-layout:fixed" border=0><tr>';
    //_cs[_cs.length] = '<table width="100%" style="table-layout:fixed" border=0><tr>';
    _cs[_cs.length] = '  <th style="margin:0px;padding:0px;"><input type="button" class="b" name="clearButton" id="clearButton" \/><\/th>';
    _cs[_cs.length] = '  <th style="margin:0px;padding:0px;'+this.getFieldStyle("y,M,d")+'"><input type="button" class="b" name="selectTodayButton" id="selectTodayButton" \/><\/th>';
    _cs[_cs.length] = '  <th style="margin:0px;padding:0px;"><input type="button" class="b"  name="confirmButton" id="confirmButton" \/><\/th>';
    _cs[_cs.length] = '  <th style="margin:0px;padding:0px;"><input type="button" class="b" name="closeButton" id="closeButton" \/><\/th>';
    _cs[_cs.length] = ' <tr></table><\/th><\/tr>';
    _cs[_cs.length] = '<\/table>';
    _cs[_cs.length] = '<\/form>';
    
    _cs[_cs.length] =   "</td> ";
    _cs[_cs.length] =  "</tr> ";
    _cs[_cs.length] = "</table> "; 
    
    this.iframe.document.body.innerHTML = _cs.join("");
    this.form = this.iframe.document.forms["__calendarForm"];

    this.form.clearButton.title = this.form.clearButton.value = Calendar.lang["clear"];
    this.form.selectTodayButton.title = this.form.selectTodayButton.value = Calendar.lang["today"];
    this.form.closeButton.title = this.form.closeButton.value = Calendar.lang["close"];
    this.form.confirmButton.title = this.form.confirmButton.value = Calendar.lang["confirm"];
    
    /****/
    var __obj = this ;
    var yss = this.el("yssId") ;
    var ys = this.el("ysId") ;
    yss.onclick = function(evt){
        __obj.showYear(2) ;
        __obj.showMonth(1) ;
    }
    var mss = this.el("mssId") ;
    var ms = this.el("msId") ;
    mss.onclick = function(){
        __obj.showMonth(2) ;
        __obj.showYear(1) ;
    }
    
    var hspanId = this.el("hspanId") ;//hour
    var minspanId = this.el("minspanId") ;//min
    hspanId.onclick = function(){
        __obj.showHour(2) ;
        __obj.showMin(1) ;
    }
    
    minspanId.onclick = function(){
        __obj.showMin(2) ;
        __obj.showHour(1) ;
    }
    
    /****/
    
    this.form.goPrevMonthButton.onclick = function () {calendar.goPrevMonth(this);}
    this.form.goNextMonthButton.onclick = function () {calendar.goNextMonth(this);}

    this.form.goPrevYearButton.onclick = function () {calendar.goPrevYear(this);}
    this.form.goNextYearButton.onclick = function () {calendar.goNextYear(this);}

    this.form.hourSelect.onchange = function(){ calendar.update(this); } ;
    this.form.minSelect.onchange = function(){ calendar.update(this); } ;

    if(__on)this.form.yearSelect.onchange = function () {calendar.update(this);}
    if(__on)this.form.monthSelect.onchange = function () {calendar.update(this);}
    
    this.form.clearButton.onclick = function () {calendar.dateControl.value = "";calendar.hide();}
    this.form.closeButton.onclick = function () {calendar.hide();}
    this.form.selectTodayButton.onclick = function () {
        var today = new Date();
        calendar.dateControl.value = calendar.getCurDateString(2,null,today);
        calendar.hide();
    }
    
    this.form.confirmButton.onclick = function () {
        var flag = this.flag ;
        calendar.dateControl.value = calendar.getCurDateString(flag);
        calendar.hide();
    }
};

Calendar.prototype.__show = function(type,spanId,id,value,extend){//\u00d0\u0094\u00ca\u00be\u00d0\u00a1\u00ca\u00b1
    var yss = this.el(spanId) ;
    var ys = this.el(id) ;
    if(type == 1){//text
        ys.style.display = 'none' ;
        yss.style.display = '' ;
        var _year  = value ;
        _year = this.zero(_year) ;
        yss.innerHTML = "<nobr>"+_year + (extend||"")+"</nobr>" ;
    }else{
        ys.style.display = '' ;
        yss.style.display = 'none' ;
    }
}

Calendar.prototype.showHour = function(type){
    if( this.getFieldStyle("h") != "" ) return ;
    this.__show(type,"hspanId","hsId",this.getHour()," "+Calendar.lang["hour"]) ;
}

Calendar.prototype.showMin = function(type){
    if( this.getFieldStyle("m") != "" ) return ;
    this.__show(type,"minspanId","minsId",this.getMin()," "+Calendar.lang["min"]) ;
}

Calendar.prototype.showYear = function(type){
    if( this.getFieldStyle("y") != "" ) return ;
    if( this.el('yearSelect').selectedIndex == -1 ){
        return ;
    }
    this.__show(type,"yssId","ysId",this.getYear(),"   "+Calendar.lang["year"]) ;
}

Calendar.prototype.el = function(id){
    return this.iframe.document.getElementById(id) ;//hour
}

Calendar.prototype.showMonth = function(type){
    if( this.getFieldStyle("M") != "" ) return ;
    if( this.el('yearSelect').selectedIndex == -1 ){
        return ;
    }
    this.__show(type,"mssId","msId",parseInt(this.getMonth())+1,"   "+Calendar.lang["month"]) ;
}

Calendar.prototype.zero = function(i){
    if((i+"").length<=1) return "0"+i ;
    return i ;
}

Calendar.prototype.bindHour = function() {
    var ys = this.form.hourSelect;
    ys.length = 0;
    for (var i = 0; i < 24 ; i++){
        ys.options[ys.length] = new Option(this.zero(i), i);
    }
};

Calendar.prototype.bindMin = function() {
    var ys = this.form.minSelect;
    ys.length = 0;
    for (var i = 0; i <= 59; i++){
        ys.options[ys.length] = new Option(this.zero(i), i);
    }
};


Calendar.prototype.bindYear = function() {
    var ys = this.form.yearSelect;
    ys.length = 0;
    for (var i = this.beginYear; i <= this.endYear; i++){
        ys.options[ys.length] = new Option(this.zero(i) , i);
    }
};

Calendar.prototype.bindMonth = function() {
    var ms = this.form.monthSelect;
    ms.length = 0;
    for (var i = 0; i < 12; i++){
        ms.options[ms.length] = new Option(this.zero(i+1), i);
    }
};


Calendar.prototype.goPrevMonth = function(e){
    if (this.year == this.beginYear && this.month == 0){return;}
    this.month--;
    if (this.month == -1) {
        this.year--;
        this.month = 11;
    }
    this.date = new Date(this.year, this.month,1);//, this.date.getDate()
    this.changeSelect();
    this.bindData();
};

Calendar.prototype.goNextMonth = function(e){
    if (this.year == this.endYear && this.month == 11){return;}
    this.month++;
    if (this.month == 12) {
        this.year++;
        this.month = 0;
    }
    this.date = new Date(this.year, this.month,1);//, this.date.getDate()
    this.changeSelect();
    this.bindData();
};

Calendar.prototype.changeSelect = function() {
    var ys = this.el('yearSelect');
    var ms = this.el('monthSelect');

    for (var i= 0; i < ys.length; i++){
        if (ys.options[i].value == this.date.getFullYear()){
            ys[i].selected = true;
            break;
        }
    }

    for (var i= 0; i < ms.length; i++){
        if (ms.options[i].value == this.date.getMonth()){
            ms[i].selected = true;
            break;
        }
    }
    this.showYear(1);
    this.showMonth(1) ;
};

Calendar.prototype.changeHourMin = function(){
    
    var ys = this.form.hourSelect;
    var ms = this.form.minSelect;

    for (var i= 0; i < ys.length; i++){
        if (ys.options[i].value == this.date.getHours()){
            ys[i].selected = true;
            break;
        }
    }
    for (var i= 0; i < ms.length; i++){
        if (ms.options[i].value == this.date.getMinutes()){
            ms[i].selected = true;
            break;
        }
    }
    this.showHour(1);
    this.showMin(1) ;
}

Calendar.prototype.goPrevYear = function(e){
    if (this.year == this.beginYear ){return;}
    this.year  = this.getYear() ;
    e.form.yearSelect.value = this.year - 1 ;
    this.year = this.year - 1 ;
    this.month = this.getMonth() ;
    this.date = new Date(this.year, this.month);//, this.date.getDate()
    this.changeSelect();
    this.bindData();
};

Calendar.prototype.goNextYear = function(e){
    if (this.year == this.endYear ){return;}
    this.year  = this.getYear() ;
    e.form.yearSelect.value = parseInt(this.year) + 1 ;
    this.year = parseInt(this.year) + 1 ;
    this.month = this.getMonth() ;
    this.date = new Date(this.year, this.month);//, this.date.getDate()
    this.changeSelect();
    this.bindData();
};

Calendar.prototype.getHour = function(){
    return this.el('hourSelect').options[this.el('hourSelect').selectedIndex].value;
}

Calendar.prototype.getMin = function(){
    return this.el('minSelect').options[this.el('minSelect').selectedIndex].value;
}

Calendar.prototype.getYear = function(){
    return this.el('yearSelect').options[this.el('yearSelect').selectedIndex].value;
}

Calendar.prototype.getMonth = function(){
    return this.el('monthSelect').options[this.el('monthSelect').selectedIndex].value;
}


Calendar.prototype.update = function (e){
    this.year  = this.getYear() ;
    this.month = this.getMonth() ;

    this.hour  = this.getHour();
    this.min   = this.getMin();
    this.date  = new Date(this.year, this.month, 1,this.hour,this.min);

    this.changeSelect();
    this.changeHourMin() ;
    this.bindData();
};

Calendar.prototype.addClass = function(el , clsName){
        var temp = el.className ;
        if(temp && temp.indexOf(clsName)!=-1) return ;
        el.className = temp +" "+clsName ;
}

Calendar.prototype.removeClass = function(el , clsName){
        var temp = el.className ;
        if(!temp || temp.indexOf(clsName)==-1) return ;
        temp = temp.replaceAll(clsName,"").trim() ;
        el.className = temp ;
}

Calendar.prototype.getCurDateString = function(flag,date,pDate){
    this.date = pDate||this.date ;
    var month = this.date.getMonth() ;
    if(flag ==1){
        month = month - 1 ;
    }else if(flag ==2){
    }else if(flag ==3){
        month = month + 1 ;
    }
    date = date||this.date.getDate() ;
    return new Date(this.date.getFullYear(),
                                month,
                                date,
                                this.date.getHours(),
                                this.date.getMinutes()).format(calendar.date2StringPattern);
}

Calendar.prototype.bindData = function () {
    var calendar = this;
    var dateArray = this.getMonthViewDateArray(this.date.getFullYear(), this.date.getMonth());
    var tds = this.getElementsByTagName("td", this.getElementById("__calendarTable", this.iframe.document));
    for(var i = 0; i < tds.length; i++) {
        tds[i].style.backgroundColor = calendar.colors["bg_over"];
        
        if( i >= this.nextStart || i<=this.lastEnd){
            this.addClass(tds[i],"old") ;
            tds[i].flag = (i >= this.nextStart)?3:1 ;           
        }else{
            this.removeClass(tds[i],"old") ;
            tds[i].flag = 2 ;
        }
        
        tds[i].onclick = null;
        tds[i].onmouseover = null;
        tds[i].onmouseout = null;
        tds[i].innerHTML = dateArray[i] || "&nbsp;";
        
        tds[i].title = this.getCurDateString(tds[i].flag,dateArray[i] || "&nbsp;");
        
        if (i > dateArray.length - 1) continue;
        if (dateArray[i]){
            tds[i].onclick = function () {
                if (calendar.dateControl){
                    //\u00ca\u00a8\u00d7\u0083\u00c2\u00b5\u00c2\u00b1\u00c7\u00b0\u00d5\u0082
                    var flag = this.flag ;
                    calendar.dateControl.value = calendar.getCurDateString(flag,this.innerHTML);
                }
                calendar.hide();
            }
            tds[i].onmouseover = function () {this.style.backgroundColor = calendar.colors["bg_out"];}
            tds[i].onmouseout  = function () {this.style.backgroundColor = calendar.colors["bg_over"];}
            
            var today = new Date();
            var currentDate = calendar.date.getDate()  ;

            if( currentDate == dateArray[i] && currentDate!=today.getDate() && tds[i].flag == 2){
                tds[i].style.backgroundColor = calendar.colors["bg_cur"];
                tds[i].onmouseover = function () {this.style.backgroundColor = calendar.colors["bg_out"];}
                tds[i].onmouseout  = function () {this.style.backgroundColor = calendar.colors["bg_cur"];}
            }
                    
            if (today.getFullYear() == calendar.date.getFullYear()) {
                if (today.getMonth() == calendar.date.getMonth()) {
                    if (today.getDate() == dateArray[i]&&tds[i].flag == 2) {
                        tds[i].style.backgroundColor = calendar.colors["bg_cur_day"];
                        tds[i].onmouseover = function () {this.style.backgroundColor = calendar.colors["bg_out"];}
                        tds[i].onmouseout  = function () {this.style.backgroundColor = calendar.colors["bg_cur_day"];}
                    }
                }
            }
        }//end if
    }//end for
};

Calendar.prototype.getMonthViewDateArray = function (y, m) {
    dateArray = [] ;
    var dayOfFirstDate = new Date(y, m, 1).getDay();
    var dateCountOfMonth = new Date(y, m + 1, 0).getDate();
    this.lastEnd = dayOfFirstDate - 1 ;
    this.nextStart   = dayOfFirstDate + dateCountOfMonth ;
    for (var i = 0; i < dateCountOfMonth; i++) {
        dateArray[i + dayOfFirstDate] = i + 1;
    }
    

    if(this.lastEnd===0||this.lastEnd>=1){
        var lastMonth = new Date(y,m,1) ;
        lastMonth.setMonth(m-1) ;
        var lastDayOfFirstDate = lastMonth.getDay();
        var lastdateCountOfMonth = new Date(y, m , 0).getDate();
        
        for(var i= this.lastEnd ;i>=0 ;i--){
            dateArray[i] = lastdateCountOfMonth + ( i - this.lastEnd ) ;
        }
    }
    
    if(this.nextStart){
        var nextMonth = new Date(y,m,1) ;
        nextMonth.setMonth(m+1) ;
        var nextDayOfFirstDate = nextMonth.getDay();
        var _nextMonth = new Date(y,m,0) ;
        _nextMonth.setMonth(m+2) ;
        var nextdateCountOfMonth = _nextMonth.getDate();
        
        for(var i=this.nextStart ;i<42 ;i++){
            dateArray[i] = i - this.nextStart + 1;
        }
    }
    
    return dateArray;
};
Calendar.stopBubble= function( evt) { 
    var e= evt||window.event; 
    if (window.event) { 
        e.cancelBubble=true; 
    } else { 
        e.stopPropagation(); 
    } 
} 

Calendar.prototype.getElementById = function(id, object){
    object = object || document;
    return document.getElementById ? object.getElementById(id) : document.all(id);
};

Calendar.prototype.getElementsByTagName = function(tagName, object){
    object = object || document;
    return document.getElementsByTagName ? object.getElementsByTagName(tagName) : document.all.tags(tagName);
};

Calendar.prototype.getAbsPoint = function (e){
    var x = e.offsetLeft;
    var y = e.offsetTop;
    while(e = e.offsetParent){
        x += e.offsetLeft;
        y += e.offsetTop;
    }
    return {"x": x, "y": y};
};


Date.prototype.format = function(style) {
    
    var o = {
        "M+" : this.getMonth() + 1, //month
        "d+" : this.getDate(),      //day
        "h+" : this.getHours(),     //hour
        "m+" : this.getMinutes(),   //minute
        "s+" : this.getSeconds(),   //second
        "w+" : "\u65e5\u4e00\u4e8c\u4e09\u56db\u4e94\u516d".charAt(this.getDay()),   //week
        "q+" : Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S"  : this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(style)) {
        style = style.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for(var k in o){
        if (new RegExp("("+ k +")").test(style)){
            style = style.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return style;
};


String.prototype.toDate = function(format) {
    //IE -|\-
    //firefox  |\-|\-|\
    var split = this.split(/\d+/).join('|\\') ;
    if(window.attachEvent) split = '|\\'+split+'|\\' ;

    var regex = eval("/^\\"+split+"$/") ;

    var fArray = format.split(regex ) ;
    var rs = this.split(regex ) ;
    var result = {} ;
    for(var i=0 ;i<rs.length ;i++){
        var fm = fArray[i] ;
        if(!fm) continue ;
        result[fm] =  rs[i] ;
    }
    result.MM = result.MM?( result.MM - 1 ):0 ;
    var date =  new Date(result.yyyy||0,result.MM, result.dd||0,result.hh||0,result.mm||0,result.ss||0);
    
    return date ;
};

String.prototype.replaceAll = function (B, A) {
    raRegExp = new RegExp(B, "g");
    return this.replace(raRegExp, A);
};

String.prototype.trim = function() { return this.replace(/(^\s*)|(\s*$)/g, ""); } 
Calendar.initLanguage() ;
var calendar = new Calendar();
Calendar_Onload =  function(){
    document.body.onclick = function(){
        calendar.hide(); 
    }
    
    var inputs = document.getElementsByTagName("input") ;
    for(var i=0 ;i<inputs.length ;i++){
        var input = inputs[i] ;
        var clz = input.className ;
        if(clz && (clz.indexOf('calendar')!=-1)){
            var cls = clz.split(" ") ;
            for(var j=0 ;j<cls.length ;j++){
                var cl = cls[j] ;
                var trimCl = cl.trim() ;
                var ts = trimCl.split(/\(|\)/) ;
                var format = 'yyyy-MM-dd' ;
                if(ts.length>=2){
                    format = ts[1] ;
                }
                input.format = format ;
                if(ts[0] == 'calendar'){
                    input.readOnly = true ;
                    input.onclick = function(evt){ calendar.show(this,this.format,evt); } ;
                }
            }
        }
    }
}

if(window.addEventListener){
    window.addEventListener('load', Calendar_Onload, false);
}else if(window.attachEvent){
    window.attachEvent("onload" , Calendar_Onload) ;
}