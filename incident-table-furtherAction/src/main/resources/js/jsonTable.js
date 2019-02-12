(function ( $ ) {
 
    $.fn.jsonTable = function( options ) {
        var settings = $.extend({
            head: [],
            json:[]
        }, options );
        this.data("settings",settings);
        var thead = $(this.selector + ' thead').append("<tr bgcolor=#D0CECE></tr>\n");
        for(var i = 0; i < settings.head.length; i++){
            
            if((i==settings.head.length-1))
			{
				//SET THE FONT COLOR SAME AS HEADER COLOR TO HIDE THE HEADER NAME CALLED AS INDEX
				$(this.selector + ' tr').append("<th style='display:none;width:0px;'><font color=#D0CECE>"+settings.head[i]+"</font></th>\n");
			}
			else
			{
				
				$(this.selector + ' tr').append("<th style='border:1px solid Black'>"+settings.head[i]+"</th>\n");			
			}
			
				
			 //alert(settings.head[i]);
        }
        return this;
    };

    $.fn.jsonTableUpdate = function( options ){
        var opt = $.extend({
            source: undefined,
            rowClass: undefined,
            callback: undefined
        }, options );
        var settings = this.data("settings");
        var sel = this.selector;
        $(this.selector + ' tbody > tr').remove();

        if(typeof opt.source == "string")
        {
            $.get(opt.source, function(data) {
                $.fn.updateFromObj(data,settings,sel, opt.rowClass, opt.callback);
            });
        }
        else if(typeof opt.source == "object")
        {
            $.fn.updateFromObj(opt.source,settings,sel, opt.rowClass, opt.callback);
        }
    }

    $.fn.updateFromObj = function(obj,settings,selector, trclass, callback){
        var row = "";
        
        for(var i = 0; i < obj.length; i++){
            if (!trclass) {
                row += "<tr >";
            } else {
                row += "<tr class='" + trclass + "'>";				
            }
            
            for (var j = 0; j < settings.json.length; j++) {
                
				
				if((j==settings.json.length-1))
				{
					//SET THE FONT COLOR AS WHITE TO HIDE THE VALUES FOR INDEX RELATED COLUMN
					row += "<td style='display:none;width:0px'><font color=white>" + obj[i][settings.json[j]] + "</font></td>"; 
				}
				else
				{
					row += "<td >" + obj[i][settings.json[j]] + "</td>"; 	
				}			
				
				//alert("i= "+i);
				//alert("j= "+j);
				
            }
            row += "</tr>";
        }
        $(selector + '> tbody:last').append(row);
        
        if (typeof callback == "function") {
            callback();
        }
        
        $(window).trigger('resize'); // trigger the resize event to reposition dialog once all the data is loaded
    }
 
}( AJS.$ || jQuery ));
