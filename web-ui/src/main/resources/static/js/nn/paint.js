
console.log("SUCCESS");

$(document).ready(function(){

    canvasSmall = document.getElementById('canvas-small');
    contextSmall = canvasSmall.getContext("2d");

    canvas = document.getElementById('canvas');
    context = canvas.getContext("2d");

    $('#canvas').mousedown(function(e){
        var mouseX = e.pageX - this.offsetLeft - this.offsetParent.offsetLeft;
        var mouseY = e.pageY - this.offsetTop - this.offsetParent.offsetTop;

        paint = true;
        addClick(mouseX, mouseY);
        redraw();
    });

    $('#canvas').mousemove(function(e){
        if(paint){
            var mouseX = e.pageX - this.offsetLeft - this.offsetParent.offsetLeft;
            var mouseY = e.pageY - this.offsetTop - this.offsetParent.offsetTop;

            addClick(mouseX, mouseY, true);
            redraw();
        }
    });

    $('#canvas').mouseup(function(e){
        paint = false;
        evaluate();
    });

    $('#canvas').mouseleave(function(e){
        paint = false;
    });

    $('.clear').click(function(e){
        clear();
    });

    $('.evaluate').click(function(e){
        evaluate();
    });

    var clickX = new Array();
    var clickY = new Array();
    var clickDrag = new Array();
    var paint;

    function addClick(x, y, dragging)
    {
        clickX.push(x);
        clickY.push(y);
        clickDrag.push(dragging);
    }

    function redraw(){
        context.clearRect(0, 0, context.canvas.width, context.canvas.height); // Clears the canvas

        context.strokeStyle = "black";
        context.lineWidth = 5;
        context.lineJoin = "round";

        for(var i=0; i < clickX.length; i++) {
            context.beginPath();
            if(clickDrag[i] && i){
                context.moveTo(clickX[i-1], clickY[i-1]);
            }else{
                context.moveTo(clickX[i]-1, clickY[i]);
            }
            context.lineTo(clickX[i], clickY[i]);
            context.closePath();
            context.stroke();
        }
    }

    function clear() {
        contextSmall.clearRect(0, 0, contextSmall.canvas.width, contextSmall.canvas.height);
        context.clearRect(0, 0, context.canvas.width, context.canvas.height); // Clears the canvas
        clickX = new Array();
        clickY = new Array();
        clickDrag = new Array();
        paint;
    }

    function evaluate() {

        transformToSmall(canvas);
        var data = data_to_array(contextSmall.getImageData(0, 0, canvasSmall.width, canvasSmall.height).data);

        $.ajax({
            type: "POST",
            url: "http://http://localhost:8080//evaluate",
            data: JSON.stringify({ image : data }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                    $(".result").html("The result is "+data.number);
                },
            failure: function(errMsg) {alert(errMsg);}
        });
    }

});


