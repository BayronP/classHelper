/**
 * Created by myski on 2015/6/23.
 */
 /** This code runs when everything has been loaded on the page */


 function draw_chart(char_data, the_chart) {
        var select_id = "#pie-chart" + the_chart;
        $(select_id).sparkline(char_data, {
            type: "pie",
            height: "220",
            width: "220",
            offset: "+90",
            sliceColors: ["#a0eeed", "#81e970", "#f5af50", "#f46f50"]
        });
 }




function get_problemset() {
    $.ajax({
        type:"GET",
        url:"http://127.0.0.1:8000/solution/.json",
        dataType:"json",
        success:function(data){
            var problem_solution = data;
            var student_num = problem_solution.length;
            var problem_num = problem_solution[0].ans.length;
            console.log(problem_num);

            //初始化存答案的二维数组
            var choices = new Array();
            for (var the_problem = 0; the_problem < problem_num; the_problem++) {
                choices[the_problem] = new Array();
                for (var choice_num = 0; choice_num < 4; choice_num++) {
             //       choice[choice_num] = 0;
                    choices[the_problem][choice_num] = 0;
                }
              //  choices[the_problem] = choice;
            }
            //console.log(choices[0][0]);
            //统计每一题里面ABCD四个选项的个数
            for (var the_proble = 0; the_proble < problem_num; the_proble++) {
                for (var the_student = 0; the_student < student_num; the_student++) {
                    if (problem_solution[the_student].ans[the_proble] == "A") {
                        choices[the_proble][0]++;
                    }
                    else if (problem_solution[the_student].ans[the_proble] == "B") {
                        choices[the_proble][1]++;
                    }
                    else if (problem_solution[the_student].ans[the_proble] == "C") {
                        choices[the_proble][2]++;
                    }
                    else if (problem_solution[the_student].ans[the_proble] == "D") {
                        choices[the_proble][3]++;
                    }
                }
            }
            console.log(choices[0][0]);
            console.log(choices);
            var chart_data;
            for (var i = 0; i < problem_num; i++) {
                chart_data = [choices[i][0], choices[i][1], choices[i][2], choices[i][3]];
                draw_chart(chart_data, i);
            }



            //var answer_data = problem_data[0].problemsAns;
            //var answer_num = answer_data.length;
            ////alert("答案");
            ////alert(answer_data);
            ////alert("Ans_num");
            ////alert(answer_num);
            //var choices = [];
            //for (var i = 0; i < 4; i++) {
            //    choices[i] = 0;
            //}
            ////统计选项数量
            //for (var i = 0; i < answer_num; i++){
            //    if(answer_data[i] == "A")choices[0]++;
            //    else if (answer_data[i] == "B") choices[1]++;
            //    else if (answer_data[i] == "C") choices[2]++;
            //    else if (answer_data[i] == "D") choices[3]++;
            //}
            ////alert("Choice 0");
            ////alert(choices[0]);
            ////alert(choices[1]);
            ////alert(choices[2]);
            ////alert(choices[3]);
            //var char_data=[choices[0], choices[1], choices[2], choices[3]];
            ////alert("Chart data");
            ////alert(char_data);
            //draw_chart(char_data);
            ////console.log(problem_length);
        }
    });
}


get_problemset();





