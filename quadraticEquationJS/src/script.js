document.addEventListener("DOMContentLoaded", start);

function start() {
    document.getElementById("calculate_button").addEventListener("click", calculate);
    function calculate() {
        var a = document.getElementById("first_argument").value;
        var b = document.getElementById("second_argument").value;
        var c = document.getElementById("third_argument").value;
        var equal = document.getElementById("equal").value;

        if (isNaN(a) || isNaN(b) || isNaN(c) || isNaN(equal)) {
            alert("Данные введены некорректно");
        } else {
            var firstAnswer = document.getElementById("fist_answer");
            var secondAnswer = document.getElementById("second_answer");

            var epsilon = 1.0e-10;
            c -= equal;

            var aIsZero = Math.abs(a) < epsilon;
            var bIsZero = Math.abs(b) < epsilon;
            var cIsZero = Math.abs(c) < epsilon;

            if (aIsZero && bIsZero && cIsZero) {
                firstAnswer.value = "+∞";
                secondAnswer.value = "-∞";
            } else if (aIsZero && bIsZero) {
                firstAnswer.value = "Решений нет";
                secondAnswer.value = "Решений нет";
            } else if (aIsZero) {
                firstAnswer.value = (-c / b).toFixed(2);
                secondAnswer.value = "Один корень";
            } else {
                var x1;
                var x2;
                var d = Math.pow(b, 2) - 4 * a * c;
                if (d >= epsilon) {
                    x1 = (-b + Math.sqrt(d)) / (2 * a);
                    x2 = (-b - Math.sqrt(d)) / (2 * a);
                    firstAnswer.value = x1.toFixed(2);
                    secondAnswer.value = x2.toFixed(2);
                } else if (Math.abs(d) < epsilon) {
                    x1 = -b / (2 * a);
                    firstAnswer.value = x1.toFixed(2);
                    secondAnswer.value = "Один корень"
                } else {
                    firstAnswer.value = "Решений нет";
                    secondAnswer.value = "Решений нет";
                }
            }
        }
    }
}
