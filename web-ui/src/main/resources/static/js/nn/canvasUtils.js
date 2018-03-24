
function data_to_array(data) {
    var result_data = [];
    var p = 0;
    for (var i=0;i<data.length;i+=4)
    {
        result_data[p] = data[i+3];
        p++;
    }
    return result_data;
};

function transformToSmall(canvasBig) {

    var canvas = document.getElementById("canvas-small");
    var ctx = canvas.getContext("2d");

    ctx.drawImage(canvasBig, 0, 0, canvas.width, canvas.height);

    var imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
    var formattedImageData = to2d28x28(data_to_array(imageData.data));

    var centerOfMass = getCenterOfMass(formattedImageData);
    var movedCenterX = (canvas.width  / 2) - centerOfMass.x;
    var movedCenterY = (canvas.height / 2) - centerOfMass.y;

    ctx.globalCompositeOperation = "copy";
    ctx.drawImage(canvas, movedCenterX, movedCenterY);
    ctx.globalCompositeOperation = "source-over"
}


function to2d28x28(data) {
    var arr = [];
    var k = 0;
    for (var i=0; i<28; i++) {
        arr.push([]);
        for (var j=0; j<28; j++) {
            arr[i].push(data[k++])
        }
    }
    return arr;
}

function getCenterOfMass(data) {

    var numX = 0;
    var numY = 0;

    var sumX = 0;
    var sumY = 0;

    for (var y=0; y<28; y++) {
        for (var x=0; x<28; x++) {
            if (data[y][x] !== 0) {
                numX ++;
                numY ++;

                sumX += x;
                sumY += y;
            }
        }
    }

    var res = {};
    res.x = Math.round(sumX / numX);
    res.y = Math.round(sumY / numY);
    return res;
}