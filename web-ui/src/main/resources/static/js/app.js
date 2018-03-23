var app = angular.module('GenAlg', []);

app.controller('index-controller', function($scope) {

    setIteration(0);

    var source = new EventSource("http://localhost:8080/genalg");

    source.onmessage = function(event) {
        setIteration(JSON.parse(event.data).numIteration)
        $scope.$apply()
    };
    source.close();

    function setIteration( iteration) {
        $scope.numIteration = iteration;
        console.log($scope.numIteration);
    }

});