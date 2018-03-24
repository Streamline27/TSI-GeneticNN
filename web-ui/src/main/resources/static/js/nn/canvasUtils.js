
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

    var minX = getMinX(formattedImageData);
    var maxX = getMaxX(formattedImageData);

    var minY = getMinY(formattedImageData);
    var maxY = getMaxY(formattedImageData);

    var croppedDimension = Math.max(maxY - minY,  maxX - minX);

    var croppedImageData = ctx.getImageData(minX-1, minY-1, croppedDimension+1, croppedDimension+1);
    var croppedCanvas = document.createElement("canvas");
    var croppedContext = croppedCanvas.getContext("2d");
    croppedCanvas.width = croppedDimension;
    croppedCanvas.height = croppedDimension;
    croppedCanvas.imageSmoothingEnabled = true;
    croppedCanvas.imageSmoothingQuality = "high";
    croppedContext.putImageData(croppedImageData, 0, 0);

    var resizedCanvas = document.createElement("canvas");
    var resizedContext = resizedCanvas.getContext("2d");
    resizedCanvas.width = 20;
    resizedCanvas.height = 20;
    resizedCanvas.imageSmoothingEnabled = true;
    resizedCanvas.imageSmoothingQuality = "high";
    resizedContext.drawImage(croppedCanvas, 0, 0, 20, 20);

    var movedCanvas = document.createElement("canvas");
    var movedContext = movedCanvas.getContext("2d");
    movedCanvas.width = 28;
    movedCanvas.height = 28;

    movedContext.drawImage(croppedCanvas, 0, 0, 20, 20);

    var movedImageData = movedContext.getImageData(0, 0, 28, 28);

    var formattedMovedImageData = to2d28x28(data_to_array(movedImageData.data));

    var centerOfMass = getCenterOfMass(formattedMovedImageData);
    var movedCenterX = (canvas.width  / 2) - centerOfMass.x;
    var movedCenterY = (canvas.height / 2) - centerOfMass.y;

    ctx.globalCompositeOperation = "copy";
    ctx.drawImage(movedCanvas, movedCenterX, movedCenterY);
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


function getMinX(data) {
    for (var x=0; x<28; x++) {
        for (var y=0; y<28; y++) {
            if (data[y][x] !== 0) {
                return x;
            }
        }
    }
    return 0;
}

function getMinY(data) {
    for (var y=0; y<28; y++) {
        for (var x=0; x<28; x++) {
            if (data[y][x] !== 0) {
                return y;
            }
        }
    }
    return 0;
}


function getMaxX(data) {
    for (var x=27; x>0; x--) {
        for (var y=27; y>0; y--) {
            if (data[y][x] !== 0) {
                return x;
            }
        }
    }
    return 28;
}

function getMaxY(data) {
    for (var y=27; y>0; y--) {
        for (var x=27; x>0; x--) {
            if (data[y][x] !== 0) {
                return y;
            }
        }
    }
    return 28;
}