/**
 * 
 */
window.onload = function() {
    var tree = document.getElementById("tree");
    var lis = tree.getElementsByTagName("li");
    for(var i = 0; i < lis.length; i++) {
        (function(a) {
            lis[a].onclick = function() {
                if(typeof this.getElementsByTagName("ul") !== null) {
                    var ul_first = this.getElementsByTagName("ul")[0];
                    if(ul_first.style.display == "block")
                        ul_first.style.display = "none";
                    else
                        ul_first.style.display = "block";
                }
            };
        })(i);
    }
};