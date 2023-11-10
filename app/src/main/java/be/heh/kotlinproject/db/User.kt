package be.heh.kotlinproject.db

class User(i:Int) {
    var id : Int = 0
        public get
        private set
    var login = "null"
        public get
        private set
    var pwd: String = "null"
        public get
        private set
    var email: String = "null"
        public get
        private set
    constructor(i: Int, l: String,
                p: String, e: String ) : this(i)
    {
        id = i
        login = l
        pwd = p
        email = e
    }
    override fun toString() : String {
        val sb = StringBuilder()
        sb.append("ID : " + id.toString() +
                "\n" +
                "Login : " + login + "\n" +
                "Password : " + pwd + "\n" +
                "Email : " + email)
        return sb.toString()
    }
}