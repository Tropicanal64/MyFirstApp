package config.fr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class config1200 : AppCompatActivity() {
    // on créer les variables:

    // BDD
    private var  database = Firebase.database

    // proc
    private lateinit var processeur: TextView
    private lateinit var prix_processeur: TextView
    private lateinit var photo_proc: ImageButton
    private lateinit var lien_proc: String

    // ram
    private lateinit var ram:TextView
    private lateinit var prix_ram:TextView
    private lateinit var photo_ram:ImageButton
    private lateinit var lien_ram:String

    // carte graphique
    private lateinit var cg:TextView
    private lateinit var prix_cg:TextView
    private lateinit var photo_cg:ImageButton
    private lateinit var lien_cg:  String

    // carte mère
    private lateinit var cm:TextView
    private lateinit var prix_cm:TextView
    private lateinit var photo_cm:ImageButton
    private lateinit var lien_cm:  String


    // SSD
    private lateinit var ssd:TextView
    private lateinit var prix_ssd:TextView
    private lateinit var photo_ssd:ImageButton
    private lateinit var lien_ssd:  String


// alimentation
    private lateinit var alim:TextView
    private lateinit var prix_alim:TextView
    private lateinit var photo_alim:ImageButton
    private lateinit var lien_alim:  String


// boitier
    private lateinit var boitier:TextView
    private lateinit var prix_boitier:TextView
    private lateinit var photo_boitier:ImageButton
    private lateinit var lien_boitier:  String

    // le total de la config
    private lateinit var total:TextView

    // la progressBar
    private lateinit var progressbar: ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config1200)

        // on défini les variables

        processeur =  findViewById(R.id.processeur)
        prix_processeur = findViewById(R.id.prix_proc)
        photo_proc = findViewById(R.id.photo_proc)

        ram = findViewById(R.id.ram)
        prix_ram = findViewById(R.id.prix_ram)
        photo_ram =  findViewById(R.id.photo_ram)

        cg = findViewById(R.id.cg)
        prix_cg = findViewById(R.id.prix_cg)
        photo_cg = findViewById(R.id.photo_cg)

        cm = findViewById(R.id.cm)
        prix_cm = findViewById(R.id.prix_cm)
        photo_cm = findViewById(R.id.photo_cm)

        ssd = findViewById(R.id.ssd)
        prix_ssd = findViewById(R.id.prix_ssd)
        photo_ssd = findViewById(R.id.photo_ssd)

        alim = findViewById(R.id.alim)
        prix_alim = findViewById(R.id.prix_alim)
        photo_alim = findViewById(R.id.photo_alim)

        boitier = findViewById(R.id.boitier)
        prix_boitier = findViewById(R.id.prix_boitier)
        photo_boitier = findViewById(R.id.photo_boitier)

        total = findViewById(R.id.total)

        progressbar = findViewById(R.id.progressbar)

        // on rend la pogresssbar visible , nous l'enleverons une fois le chargement des bdd fini
        progressbar.visibility = VISIBLE


        // on lit dans la bdd les infos sur le processeur
        read("processeur", processeur)
        read("prix_processeur", prix_processeur)
        readLienProc()

        // on lit dans la bdd les infos sur la ram
        read("ram", ram)
        read("prix_ram", prix_ram)
        readLienRam()

        // on lit dans la bdd les infos sur la carte graphique
        read("cg", cg)
        read("prix_cg", prix_cg)
        readLienCg()

        // on lit dans la bdd les infos sur la carte mere
        read("cm", cm)
        read("prix_cm", prix_cm)
        readLienCm()

        // on lit dans la bdd les infos sur le ssd
        read("ssd", ssd)
        read("prix_ssd", prix_ssd)
        readLienSsd()

        // on lit dans la bdd les infos sur l'alimentation
        read("alim", alim)
        read("prix_alim", prix_alim)
        readLienAlim()

        // on lit dans la bdd les infos sur le boitier
        read("boitier", boitier)
        read("prix_boitier", prix_boitier)
        readLienBoitier()

        // on lit dans la bdd les infos sur le total
        read("total", total)


        // Pour les boutons, s'ils sont clickés alors on ouvre l'url  du produit
        photo_proc.setOnClickListener{
            var url = lien_proc
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        photo_ram.setOnClickListener{
            var url = lien_ram
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        photo_cg.setOnClickListener{
            var url = lien_cg
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        photo_cm.setOnClickListener{
            var url = lien_cm
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        photo_ssd.setOnClickListener{
            var url = lien_ssd
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        photo_alim.setOnClickListener{
            var url = lien_alim
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        photo_boitier.setOnClickListener{
            var url = lien_boitier
            var i = Intent(Intent.ACTION_VIEW,  Uri.parse(url))
            startActivity(i)
        }




    }

    // fonctions permettant de lire dans la BDD
    private fun read(child: String, id: TextView){
        database.getReference("Config 1200").child(child)
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    id.text = value

                }

            })

    }

    private fun readLienProc(){
        database.getReference("Config 1200").child("lien_processeur")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value
                    lien_proc = value.toString()
                }

            })
    }

    private fun readLienRam(){
        database.getReference("Config 1200").child("lien_ram")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value
                    lien_ram = value.toString()
                }

            })
    }

    private fun readLienCg(){
        database.getReference("Config 1200").child("lien_cg")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value
                    lien_cg = value.toString()
                }

            })
    }
    private fun readLienCm(){
        database.getReference("Config 1200").child("lien_cm")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value
                    lien_cm = value.toString()
                }

            })
    }

    private fun readLienSsd(){
        database.getReference("Config 1200").child("lien_ssd")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value
                    lien_ssd = value.toString()
                }

            })
    }

    private fun readLienAlim(){
        database.getReference("Config 1200").child("lien_alim")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value
                    lien_alim = value.toString()
                }

            })


    }

    private fun readLienBoitier(){
        database.getReference("Config 1200").child("lien_boitier")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value
                    lien_boitier = value.toString()
                    progressbar.visibility = GONE
                }

            })


    }
}


