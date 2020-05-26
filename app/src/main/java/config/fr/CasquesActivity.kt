package config.fr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class CasquesActivity : AppCompatActivity() {

// creeation de la variable database
    private  var database = Firebase.database

    // creation de la variable progressBar
    private lateinit var progressbar:ProgressBar

// creation des variables du casque1
    private lateinit var casque1btn: LinearLayout
    private lateinit var casque1:TextView
    private lateinit var prix_casque1:TextView
    private lateinit var lien_casque1:String

    // creation des variables du casque2
    private lateinit var casque2btn: LinearLayout
    private lateinit var casque2:TextView
    private lateinit var prix_casque2:TextView
    private lateinit var lien_casque2:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casques)

        // on défini la progressBar
        progressbar = findViewById(R.id.progressbar)

        // on rend la progressbar visible
        progressbar.visibility = VISIBLE

        // on défini les variables cassque1
        casque1btn = findViewById(R.id.casque1btn)
        casque1 = findViewById(R.id.casque1)
        prix_casque1 = findViewById(R.id.prix_casque1)

        // on défini les variables cassque2
        casque2btn = findViewById(R.id.casque2btn)
        casque2 = findViewById(R.id.casque2)
        prix_casque2 = findViewById(R.id.prix_casque2)

        // a l'aide de la fonction read, on lit dans la base de donnéess les informations :
        // ( casque1: le nom du casque, prix_casque1: le prix du casque)
        read("casque1", casque1)
        read("prix_casque1", prix_casque1)

        // a l'aide de la fonction readLienCasque1 on lit l'info:
        // lien_casque1 : le lien qui  permet de se rediriger vers le casque en question
        readLienCasque1()

        // a l'aide de la fonction read, on lit dans la base de donnéess les informations :
        // ( casque1: le nom du casque, prix_casque1: le prix du casque)
        read("casque2", casque2)
        read("prix_casque2", prix_casque2)

        // a l'aide de la fonction readLienCasque2 on lit l'info:
        // lien_casque2 : le lien qui  permet de se rediriger vers le casque en question
        readLienCasque2()

        // si le bouton est cliqué on ouvre l'url
        casque1btn.setOnClickListener{
            var url = lien_casque1
            var i  = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        // si le bouton est cliqué on ouvre l'url
        casque2btn.setOnClickListener{
            var url = lien_casque2
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }
    }


    // fonction permettant de lire dans la base de données
    private fun read(child:String, id:TextView){
        database.getReference("casques").child(child)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    id.text = value
                }

            })
            }

    // fonction permettant dee lire le lien du casque1 dans la base de donnée
    private fun readLienCasque1(){
        database.getReference("casques").child("lien_casque1")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_casque1 = value
                    // on desactive la progress bar car le chargment est fini

                }

            })
    }

    // fonction permettant dee lire le lien du casque2 dans la base de donnée
    private fun readLienCasque2(){
        database.getReference("casques").child("lien_casque2")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_casque2 = value
                    // on desactive la progress bar car le chargment est fini
                    progressbar.visibility = GONE

                }

            })
    }
}
