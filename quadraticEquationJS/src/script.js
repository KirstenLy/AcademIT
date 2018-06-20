window.onload = function () {

        document.getElementById("calculate_button").onclick = function () {
            var a = document.getElementById("first_argument").value;
            var b = document.getElementById("second_argument").value;
            var c = document.getElementById("third_argument").value;
            var equal = document.getElementById("equal").value;

            if (isNaN(a) || isNaN(b) || isNaN(c) || isNaN(equal)) {
                alert("Данные введены некорректно");
            }
            else {
                var first_answer = document.getElementById("fist_answer");
                var second__answer = document.getElementById("second_answer");

                var epsilon = 1.0e-10;
                c -= equal;

                var aIsZero = Math.abs(a) < epsilon;
                var bIsZero = Math.abs(b) < epsilon;
                var cIsZero = Math.abs(c) < epsilon;

                if (aIsZero && bIsZero && cIsZero) {
                    first_answer.value = "+∞";
                    second__answer.value = "-∞";
                } else if (aIsZero && bIsZero) {
                    first_answer.value = "Решений нет";
                    second__answer.value = "Решений нет";
                } else if (aIsZero) {
                    first_answer.value = (-c / b).toFixed(2);
                    second__answer.value = "Один корень";
                } else {
                    var x1;
                    var x2;
                    var d = Math.pow(b, 2) - 4 * a * c;
                    if (d >= epsilon) {
                        x1 = (-b + Math.sqrt(d)) / (2 * a);
                        x2 = (-b - Math.sqrt(d)) / (2 * a);
                        first_answer.value = x1.toFixed(2);
                        second__answer.value = x2.toFixed(2);
                    } else if (Math.abs(d) < epsilon) {
                        x1 = -b / (2 * a);
                        first_answer.value = x1.toFixed(2);
                        second__answer.value = "Один корень"
                    } else {
                        first_answer.value = "Решений нет";
                        second__answer.value = "Решений нет";
                    }
                }
            }
        }

};
