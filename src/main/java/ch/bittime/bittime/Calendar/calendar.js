/**
 * @author Robin
 */
const monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];



var count = 0;
var yearc = 0;
document.getElementById("cMonthYear").innerHTML = "adasdad";
var today = new Date();
var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
var d = new Date();
var dayName = days[d.getDay()];

var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();


var firstDay = new Date(today.getFullYear(), today.getMonth(), 1);
var l = new Date(today.getFullYear(), today.getMonth() + 1, 0).getDate();
var lLastM = new Date(today.getFullYear(), today.getMonth(), 0).getDate()
var fd = firstDay.toString().substring(0, 3);
var monthName = monthNames[today.getMonth()];
today = dd + '/' + mm + '/' + yyyy;
document.getElementById("currentDay").innerHTML = dayName;



calendar(lLastM, fd, l, monthName);


function calendar(){
    document.getElementById("cMonthYear").innerHTML = monthName;
    if (fd == "Mon") {

        var o = 1;
        for (var c = 1; c < 42; c++) {
            var f = c
            var x = c - 1

            if (x == l || x > l) {
                document.getElementById("f" + f).innerHTML = o.toString();
                o++;
            } else {
                document.getElementById("f" + f).innerHTML = c.toString()
            }
        }
    } else if (fd == "Tue") {
        var o = 1;
        for (var c = 1; c < 42; c++) {
            var f = c + 1
            var x = c - 1
            document.getElementById("f1").innerHTML = lLastM.toString();
            if (x == l || x > l) {
                document.getElementById("f" + f).innerHTML = o.toString();
                o++;
            } else {
                document.getElementById("f" + f).innerHTML = c.toString()
            }
        }
    } else if (fd == "Wed") {
        var o = 1;
        for (var c = 1; c < 42; c++) {
            var f = c + 2
            var x = c - 1

            var h2 = lLastM - 1;
            document.getElementById("f1").innerHTML = h2.toString();
            document.getElementById("f2").innerHTML = lLastM.toString();
            if (x == l || x > l) {
                document.getElementById("f" + f).innerHTML = o.toString();
                o++;
            } else {
                document.getElementById("f" + f).innerHTML = c.toString()
            }
        }

    } else if (fd == "Thu") {

        var o = 1;
        for (var c = 1; c < 42; c++) {
            var f = c + 3
            var x = c - 1
            var h1 = lLastM - 2;
            var h2 = lLastM - 1;
            document.getElementById("f1").innerHTML = h1.toString();
            document.getElementById("f2").innerHTML = h2.toString();
            document.getElementById("f3").innerHTML = lLastM.toString();
            if (x == l || x > l) {
                document.getElementById("f" + f).innerHTML = o.toString();
                o++;
            } else {
                document.getElementById("f" + f).innerHTML = c.toString()
            }
        }

    } else if (fd == "Fri") {
        var o = 1;
        for (var c = 1; c < 42; c++) {
            var f = c + 4
            var x = c - 1
            var h1 = lLastM - 3;
            var h2 = lLastM - 2;
            var h3 = lLastM - 1;
            document.getElementById("f1").innerHTML = h1.toString();
            document.getElementById("f2").innerHTML = h2.toString();
            document.getElementById("f3").innerHTML = h3.toString();
            document.getElementById("f4").innerHTML = lLastM.toString();
            if (x == l || x > l) {
                document.getElementById("f" + f).innerHTML = o.toString();
                o++;
            } else {
                document.getElementById("f" + f).innerHTML = c.toString()
            }
        }

    } else if (fd == "Sat") {
        var o = 1;
        for (var c = 1; c < 42; c++) {
            var f = c + 5
            var x = c - 1
            var h1 = lLastM - 4;
            var h2 = lLastM - 3;
            var h3 = lLastM - 2;
            var h4 = lLastM - 1;
            document.getElementById("f1").innerHTML = h1.toString();
            document.getElementById("f2").innerHTML = h2.toString();
            document.getElementById("f3").innerHTML = h3.toString();
            document.getElementById("f4").innerHTML = h4.toString();
            document.getElementById("f5").innerHTML = lLastM.toString();
            if (x == l || x > l) {
                document.getElementById("f" + f).innerHTML = o.toString();
                o++;
            } else {
                document.getElementById("f" + f).innerHTML = c.toString()
            }
        }

    } else if (fd == "Sun") {
        var o = 1;
        for (var c = 1; c < 42; c++) {
            var f = c + 6
            var x = c - 1
            var h1 = lLastM - 5;
            var h2 = lLastM - 4;
            var h3 = lLastM - 3;
            var h4 = lLastM - 2;
            var h4 = lLastM - 1;
            document.getElementById("f1").innerHTML = h1.toString();
            document.getElementById("f2").innerHTML = h2.toString();
            document.getElementById("f3").innerHTML = h3.toString();
            document.getElementById("f4").innerHTML = h4.toString();
            document.getElementById("f5").innerHTML = h5.toString();
            document.getElementById("f6").innerHTML = lLastM.toString();
            if (x == l || x > l) {
                document.getElementById("f" + f).innerHTML = o.toString();
                o++;
            } else {
                document.getElementById("f" + f).innerHTML = c.toString()
            }
        }

    }
}
var s = lastDay.toString();
//document.getElementById("currentDay").innerHTML = dayName;

//document.getElementById("cMonthYear").innerHTML = dd + "/" + mm + "/" + yyyy;


function lastMonth() {
    count++;
    cc = count-1;
    today = new Date();
    check = monthNames[today.getMonth() - count];
    if(check.toString() == "January") {
        yearc++;
        firstDay = new Date(today.getFullYear() - yearc, today.getMonth() - count, 1);
        l = new Date(today.getFullYear() - yearc, today.getMonth() - count, 0).getDate();
        monthName = monthNames[today.getMonth() - count];
        firstDay = new Date(today.getFullYear()- yearc, today.getMonth() -count, 1);
        l = new Date(today.getFullYear()- yearc, today.getMonth() - cc, 0).getDate();
        lLastM = new Date(today.getFullYear()- yearc, today.getMonth()-count, 0).getDate();
    }else {
        firstDay = new Date(today.getFullYear(), today.getMonth() - count, 1);
        l = new Date(today.getFullYear(), today.getMonth() - count, 0).getDate();
        monthName = monthNames[today.getMonth() - count];
        firstDay = new Date(today.getFullYear(), today.getMonth() -count, 1);
        l = new Date(today.getFullYear(), today.getMonth() - cc, 0).getDate();
        lLastM = new Date(today.getFullYear(), today.getMonth()-count, 0).getDate();
    }
     days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
     d = new Date();
     dayName = days[d.getDay()];

     dd = String(today.getDate()).padStart(2, '0');
    mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
     yyyy = today.getFullYear();




    fd = firstDay.toString().substring(0, 3);
    today = dd + '/' + mm + '/' + yyyy;
    calendar(lLastM, fd, l, monthName);

}
function nextMonth() {
    count--;
    cc = count+1;
    today = new Date();
    firstDay = new Date(today.getFullYear(), today.getMonth() - count, 1);
    firstDay = new Date(today.getFullYear(), today.getMonth() - count, );
    l = new Date(today.getFullYear(), today.getMonth() - count, 0).getDate();
    monthName = monthNames[today.getMonth() - count];


    days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    d = new Date();
    dayName = days[d.getDay()];

    dd = String(today.getDate()).padStart(2, '0');
    mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    yyyy = today.getFullYear();


    firstDay = new Date(today.getFullYear(), today.getMonth() -count, 1);
    l = new Date(today.getFullYear(), today.getMonth() - cc, 0).getDate();
    lLastM = new Date(today.getFullYear(), today.getMonth()-count, 0).getDate();

    fd = firstDay.toString().substring(0, 3);
    document.getElementById("cMonthYear").innerHTML = lLastM;
    today = dd + '/' + mm + '/' + yyyy;
    calendar(lLastM, fd, l, monthName);


}



