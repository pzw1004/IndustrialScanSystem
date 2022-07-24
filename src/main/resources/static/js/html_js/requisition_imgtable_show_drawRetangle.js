var layer=0;


CanvasExt = {
    drawRect:function(canvasId,penColor,strokeWidth){
        var that=this;
        that.penColor=penColor;
        that.penWidth=strokeWidth;

        var canvas=document.getElementById(canvasId);
        //canvas 的矩形框
        var canvasRect = canvas.getBoundingClientRect();
        //矩形框的左上角坐标
        var canvasLeft=canvasRect.left;
        var canvasTop=canvasRect.top;

        var layerIndex=layer;
        var layerName="layer";
        var x=0;
        var y=0;

        //鼠标点击按下事件，画图准备
        canvas.onmousedown=function(e){
            //设置画笔颜色和宽度
            var color=that.penColor;
            var penWidth=that.penWidth;

            layerIndex++;
            layer++;
            layerName+=layerIndex;
            x = e.clientX-canvasLeft;
            y = e.clientY-canvasTop;

            // $("#"+canvasId).addLayer({
            //     type: 'rectangle',
            //     strokeStyle: color,
            //     strokeWidth: penWidth,
            //     name:layerName,
            //     fromCenter: false,
            //     x: x, y: y,
            //     width: 1,
            //     height: 1
            // });

            $("#"+canvasId).drawLayers();
            $("#"+canvasId).saveCanvas();
            //鼠标移动事件，画图
            canvas.onmousemove=function(e){
                width = e.clientX-canvasLeft-x;
                height = e.clientY-canvasTop-y;

                $("#"+canvasId).removeLayer(layerName);

                $("#"+canvasId).addLayer({
                    type: 'rectangle',
                    strokeStyle: color,
                    strokeWidth: penWidth,
                    name:layerName,
                    fromCenter: false,
                    x: x, y: y,
                    width: width,
                    height: height
                });

                $("#"+canvasId).drawLayers();
            }
        };

        canvas.onmouseup=function(e){

            var color=that.penColor;
            var penWidth=that.penWidth;

            canvas.onmousemove=null;

            width = e.clientX-canvasLeft-x;
            height = e.clientY-canvasTop-y;

            $("#"+canvasId).removeLayer(layerName);

            $("#"+canvasId).addLayer({
                type: 'rectangle',
                strokeStyle: color,
                strokeWidth: penWidth,
                name:layerName,
                fromCenter: false,
                x: x, y: y,
                width: width,
                height: height
            });

            alert('x是'+x+'y是'+y+'宽是'+width+'高是'+height);
            window.location.href="requisitionImgtableShow?permission="+permission+"&id="+id;

            $("#"+canvasId).drawLayers();
            $("#"+canvasId).saveCanvas();
        }
    }
};

drawPen();
function drawPen(){
    var color = "red";
    var width = 1;

    CanvasExt.drawRect("drawRetangleCanvas",color,width);
}