package be.heh.kotlinproject

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.room.*
import be.heh.kotlinproject.db.MyDB
import be.heh.kotlinproject.db.User
import be.heh.kotlinproject.db.UserRecord
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun xmlClickEvent(v: View) {
        when (v.id) {
// R.id.bt_Ecrire_main -> {
            bt_Ecrire_main.id -> ecrire()
            bt_Lire_main.id -> lire()
            bt_LireLogin_main.id -> lireCeLogin(et_children_login.text.toString())
            bt_wbHeh_main.id -> afficherSiteWeb()
        }
    }
    private fun lireCeLogin(l: String) {
        /*AsyncTask.execute {
            val db = Room.databaseBuilder(
                applicationContext,
                MyDB::class.java, "MyDataBase"
            ).build()
            val dao = db.userDao()
            val dbL = dao?.get(l)
            var uL = User(dbL?.id?:0, dbL?.login?:"INDEFINI", dbL?.pwd?:"INDEFINI",
                dbL?.email?:"INDEFINI")
            tv_login_main.setTextColor(Color.RED)
            tv_main_pwd.setTextColor(Color.RED)
            tv_email_main.setTextColor(Color.RED)
            tv_login_main.text = "LOGIN : " + uL.login
            tv_main_pwd.text = "PASSWORD : " + uL.pwd
            tv_email_main.text = "EMAIL : " + uL.email
        }*/
        AsyncTask.execute {
            val db = Room.databaseBuilder(
                applicationContext,
                MyDB::class.java, "MyDataBase"
            ).build()
            val dao = db.userDao()
            val dbL = dao?.get(l)
            var uL = User(dbL?.id ?: 0, dbL?.login ?: "INDEFINI", dbL?.pwd ?: "INDEFINI", dbL?.email ?: "INDEFINI")

            // Utilisez runOnUiThread pour mettre à jour les vues UI depuis le thread principal
            runOnUiThread {
                tv_login_main.setTextColor(Color.RED)
                tv_main_pwd.setTextColor(Color.RED)
                tv_email_main.setTextColor(Color.RED)
                tv_login_main.text = "LOGIN : " + uL.login
                tv_main_pwd.text = "PASSWORD : " + uL.pwd
                tv_email_main.text = "EMAIL : " + uL.email
            }
        }
    }
    private fun ecrire() {
        val u = User(
            0, et_children_login.text.toString(),
            et_children_pwd.text.toString(),
            et_children_email.text.toString()
        )
        AsyncTask.execute({
            val db = Room.databaseBuilder(
                applicationContext,
                MyDB::class.java, "MyDataBase"
            ).build()
            val dao = db.userDao()
            val u1 = UserRecord(0, u.login, u.pwd, u.email)
            dao.insertUser(u1)
        })
        Toast.makeText(this,u.toString(),Toast.LENGTH_LONG).show()
    }
    private fun lire() {
        AsyncTask.execute({
            val db = Room.databaseBuilder(
                applicationContext,
                MyDB::class.java, "MyDataBase"
            ).build()
            val dao = db.userDao()
            val liste = dao.get()
            liste.forEach { item -> Log.i("READ", item.toString()) }
        })
    }
    private fun afficherSiteWeb() {
        val iWebSite = Intent(this,WebviewActivity::class.java)
        startActivity(iWebSite)
    }
}