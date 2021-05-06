/**
 * @author Robin
 */
const monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];


    var count = 0;
    let monthCounter = 0;
    var yearc = 0;
  //  document.getElementById("cMonthYear").innerHTML = "adasdad";
    var today = new Date();
    var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    var d = new Date();
    var dayName = days[d.getDay()];

    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();


    var firstDay = new Date(today.getFullYear(), today.getMonth(), 1);
    var l = new Date(today.getFullYear(), today.getMonth() + 1, 0).getDate(); //lastday of month
    var lLastM = new Date(today.getFullYear(), today.getMonth(), 0).getDate() //lastday last month
    var fd = firstDay.toString().substring(0, 3);
    var monthName = monthNames[today.getMonth()];
    today = dd + '/' + mm + '/' + yyyy;
    document.getElementById("currentDay").innerHTML = dd +" / " + dayName;
document.getElementById("Year").innerHTML = yyyy;

    calendar(lLastM, fd, l, monthName, firstDay);


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
            let f = c + 6
            let x = c - 1
            let h1 = lLastM - 5;
            let h2 = lLastM - 4;
            let h3 = lLastM - 3;
            let h4 = lLastM - 2;
            let h5 = lLastM - 1;
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
    document.getElementById("sick2").innerHTML = "" + count;
    if(yearc > 0 ){
        if(yearc - 1 > 0 && monthCounter == 12){
            monthCounter = 0;
        }
        var monthCheck = today.getMonth();
        var monthCheckCounter = 11 - monthCheck;
        var monthMultiply = monthCheckCounter*2;
        count = monthCheckCounter - monthMultiply;
        count = count + monthCounter;
   //     document.getElementById("sick2").innerHTML = "" + count;
        monthCounter++;

    }
    check = monthNames[today.getMonth() - count];

    if(check.toString() == "January" ) {
        firstDay = new Date(today.getFullYear() - yearc, today.getMonth() - count, 1);
        l = new Date(today.getFullYear()  - yearc, today.getMonth() - count, 0).getDate();
        monthName = monthNames[today.getMonth() - count];
      //  document.getElementById("sick2").innerHTML = monthName;
        firstDay = new Date(today.getFullYear()  - yearc, today.getMonth() -count, 1);
        l = new Date(today.getFullYear()  - yearc, today.getMonth() - cc, 0).getDate();
        lLastM = new Date(today.getFullYear() - yearc, today.getMonth()-count, 0).getDate();
        yyyy = today.getFullYear() - yearc;

        yearc++;

    } else {
       // document.getElementById("sick2").innerHTML = yearc;
        firstDay = new Date(today.getFullYear() - yearc, today.getMonth() - count, 1);
        l = new Date(today.getFullYear()  - yearc, today.getMonth() - count, 0).getDate();


            monthName = monthNames[today.getMonth() - count];
    //    document.getElementById("sick2").innerHTML = yearc;
        firstDay = new Date(today.getFullYear()  - yearc, today.getMonth() -count, 1);

        l = new Date(today.getFullYear()  - yearc, today.getMonth() - cc, 0).getDate();
        lLastM = new Date(today.getFullYear() - yearc, today.getMonth()-count, 0).getDate();
        yyyy = today.getFullYear() - yearc;
    }
     days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
     d = new Date();
     dayName = days[d.getDay()];

     dd = String(today.getDate()).padStart(2, '0');
    mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!



    document.getElementById("Year").innerHTML = yyyy;

    fd = firstDay.toString().substring(0, 3);
    today = dd + '/' + mm + '/' + yyyy;
    calendar(lLastM, fd, l, monthName, firstDay);

}
function nextMonth() {
    count--;
    cc = count+1;
    today = new Date();
    document.getElementById("sick2").innerHTML = "" + count;
    if(yearc != 0){
        if(yearc + 1 != 0 && monthCounter == -12){
            monthCounter = 0;
        }
        var monthCheck = today.getMonth();
        var monthCheckCounter = 11 - monthCheck;
        var monthCheckCounter2 = 11 - monthCheckCounter;
        var monthMultiply = monthCheckCounter2*2;
        count = monthMultiply -  monthCheckCounter2 ;
        count = count + monthCounter;
         //   document.getElementById("sick2").innerHTML = "" + monthCounter;
        monthCounter--;

    }

    check = monthNames[today.getMonth() - count];
    document.getElementById("sick2").innerHTML = "" + check;
    if(check.toString() == "December") {
        firstDay = new Date(today.getFullYear() - yearc, today.getMonth() - count, 1);
        l = new Date(today.getFullYear()  - yearc, today.getMonth() - count, 0).getDate();
        monthName = monthNames[today.getMonth() - count];
        //  document.getElementById("sick2").innerHTML = monthName;
        firstDay = new Date(today.getFullYear()  - yearc, today.getMonth() -count, 1);
        l = new Date(today.getFullYear()  - yearc, today.getMonth() - cc, 0).getDate();
        lLastM = new Date(today.getFullYear() - yearc, today.getMonth()-count, 0).getDate();
        yyyy = today.getFullYear() - yearc;
        yearc--;

    }
    else {
        // document.getElementById("sick2").innerHTML = yearc;
        firstDay = new Date(today.getFullYear() - yearc, today.getMonth() - count, 1);
        l = new Date(today.getFullYear()  - yearc, today.getMonth() - count, 0).getDate();


        monthName = monthNames[today.getMonth() - count];
        //    document.getElementById("sick2").innerHTML = yearc;
        firstDay = new Date(today.getFullYear()  - yearc, today.getMonth() -count, 1);

        l = new Date(today.getFullYear()  - yearc, today.getMonth() - cc, 0).getDate();
        lLastM = new Date(today.getFullYear() - yearc, today.getMonth()-count, 0).getDate();
        yyyy = today.getFullYear() - yearc;
    }

    days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    d = new Date();
    dayName = days[d.getDay()];

    dd = String(today.getDate()).padStart(2, '0');
    mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!


    document.getElementById("Year").innerHTML = yyyy;


    fd = firstDay.toString().substring(0, 3);

    today = dd + '/' + mm + '/' + yyyy;
    calendar(lLastM, fd, l, monthName, firstDay);


}

function showDay(id) {

    if (id == "f"+1 || id == "f"+8 || id == "f"+15 || id == "f"+22 || id == "f"+29 || id == "f"+36) {
        document.getElementById("currentDay").innerHTML = document.getElementById(id).innerHTML + " / " + "Monday";
    } else if (id =="f"+ 2 || id == "f"+9 || id == "f"+16 || id == "f"+23 || id =="f"+ 30 || id == "f"+37) {
        document.getElementById("currentDay").innerHTML = document.getElementById(id).innerHTML + " / " + "Tuesday";
    } else if (id =="f"+ 3 || id == "f"+10 || id =="f"+ 17 || id =="f"+ 24 || id =="f"+ 31 || id == "f"+38) {
        document.getElementById("currentDay").innerHTML = document.getElementById(id).innerHTML + " / " + "Wednesday";
    } else if (id =="f"+ 4 || id == "f"+11 || id == "f"+18 || id =="f"+ 25 || id == "f"+32 || id == "f"+39) {
        document.getElementById("currentDay").innerHTML = document.getElementById(id).innerHTML + " / " + "Thursday";
    } else if (id == "f"+5 || id == "f"+12 || id =="f"+ 19 || id == "f"+26 || id =="f"+33 || id == "f"+40) {
        document.getElementById("currentDay").innerHTML = document.getElementById(id).innerHTML + " / " + "Friday";
    } else if (id == "f"+6 || id =="f"+ 13 || id == "f"+20 || id == "f"+27 || id == "f"+34 || id == "f"+41) {
        document.getElementById("currentDay").innerHTML = document.getElementById(id).innerHTML + " / " + "Saturday";
    } else {
        document.getElementById("currentDay").innerHTML = document.getElementById(id).innerHTML + " / " + "Sunday";
    }
}

