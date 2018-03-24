var app = angular.module('GenAlg', []);

app.controller('index-controller', function($scope) {

    $scope.numIteration = 0;

    var connectionNum = 0;
    var source = new EventSource("http://localhost:8080/genalg");

    source.onopen = function () {
        connectionNum++;
        if (connectionNum>1) {
            source.close();
            connectionNum = 0;
        }

    };

    source.onmessage = function(event) {
        result = JSON.parse(event.data);

        $scope.numIteration = result.iterationNumber;
        drawResults(result.topGenomes);

        $scope.$apply()
    };


    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");

    function drawResults(genomes) {
        ctx.clearRect(0, 0, c.width, c.height);

        var nextPostX = 0;
        var nextPostY = 0;

        for (i = 0; i < genomes.length; i++) {
            nextPostX += 30;
            if ((i % 8) === 0) {
                nextPostY += 30;
                nextPostX = 0;
            }

            setImage(genomes[i].code, nextPostX, nextPostY);
        }
    }


    function setImage(imageData, x, y) {


        var imgData = ctx.createImageData(28, 28);

        var dot = 0;
        for (var i=0;i<imgData.data.length;i+=4)
        {
            var code = imageData[dot++];
            imgData.data[i+0]=255-code;
            imgData.data[i+1]=255-code;
            imgData.data[i+2]=255-code;
            imgData.data[i+3]=255;
        }

        ctx.putImageData(imgData, x, y);
    }


});