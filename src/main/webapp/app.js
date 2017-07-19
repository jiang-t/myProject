cola.route("/", {
    redirectTo: "#/home"
});
cola.route("/home", {
    title: "home",
    templateUrl: "home/home.html",
    jsUrl: "home/home.js"
});
cola.route("/home/head", {
    title: "head",
    templateUrl: "home/head.html"
});