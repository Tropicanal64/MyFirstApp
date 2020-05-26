package config.fr

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class config800 : AppCompatActivity() {
    private val database = Firebase.database

    // progress bar
    private lateinit var progressbar:ProgressBar

// proc
    private lateinit var processeur:TextView
    private lateinit var prix_proc:TextView
    private lateinit var photoProc:ImageButton
    private lateinit var lien_proc:String
// ram
    private lateinit var ram:TextView
    private lateinit var prix_ram:TextView
    private lateinit var photoRam:ImageButton
    private lateinit var lien_ram:String

// cg
    private lateinit var cg:TextView
    private lateinit var prix_cg:TextView
    private lateinit var photo_cg:ImageButton
    private lateinit var lien_cg:String
// cm
    private lateinit var cm:TextView
    private lateinit var prix_cm:TextView
    private lateinit var photo_cm:ImageButton
    private lateinit var lien_cm:String
// ssd
    private lateinit var ssd:TextView
    private lateinit var prix_ssd:TextView
    private lateinit var photo_ssd:ImageButton
    private lateinit var lien_ssd:String
// alim
    private lateinit var alim:TextView
    private lateinit var prix_alim:TextView
    private lateinit var photo_alim:ImageButton
    private lateinit var lien_alim:String
// boitier
    private lateinit var boitier:TextView
    private lateinit var prix_boitier:TextView
    private lateinit var photo_boitier:ImageButton
    private lateinit var lien_boitier:String

    //total
    private lateinit var total:TextView








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config800)

        // on defini la progressbar
        progressbar = findViewById(R.id.progressbar)
        // on la rend visible
        progressbar.visibility = VISIBLE


        // On defini les variable processeur
        processeur = findViewById(R.id.processeur)
        prix_proc = findViewById(R.id.prix_proc)
        photoProc = findViewById(R.id.photo_proc)

        // On defini les variable ram
        ram = findViewById(R.id.ram)
        prix_ram = findViewById(R.id.prix_ram)
        photoRam = findViewById(R.id.photo_ram)

        // On defini les variable cg
        cg = findViewById(R.id.cg)
        prix_cg = findViewById(R.id.prix_cg)
        photo_cg = findViewById(R.id.photo_cg)

        // on défini les variables cm
        cm = findViewById(R.id.cm)
        prix_cm = findViewById(R.id.prix_cm)
        photo_cm = findViewById(R.id.photo_cm)

        // on défini les variables ssd
        ssd = findViewById(R.id.ssd)
        prix_ssd = findViewById(R.id.prix_ssd)
        photo_ssd = findViewById(R.id.photo_ssd)

        // on défini les variables alim
        alim = findViewById(R.id.alim)
        prix_alim = findViewById(R.id.prix_alim)
        photo_alim = findViewById(R.id.photo_alim)

        // on défini les variables boitier
        boitier = findViewById(R.id.boitier)
        prix_boitier = findViewById(R.id.prix_boitier)
        photo_boitier = findViewById(R.id.photo_boitier)

        // on defini la variable total
        total = findViewById(R.id.total)





    // on affiche les infos en appelant les fonctions
        read("processeur", processeur)
        read("prix_processeur", prix_proc)
        readLienProcesseur()

        read("ram", ram)
        read("prix_ram", prix_ram)
        readLienRam()

        read("cm", cm)
        read("prix_cm", prix_cm)
        readLienCM()

        read("cg", cg)
        read("prix_cg", prix_cg)
        readLienCg()

        read("boitier", boitier)
        read("prix_boitier", prix_boitier)
        readLienBoitier()

        read("alim", alim)
        read("prix_alim", prix_alim)
        readLienAlim()

        read("ssd", ssd)
        read("prix_ssd", prix_ssd)
        readLienSSD()

        read("total", total)





    // bouton qui sert à rediriger l'utilisateur vers l'url du proc
        photoProc.setOnClickListener{
            val url = lien_proc
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    // bouton qui sert à rediriger l'utilisateur vers l'url de la ram
        photoRam.setOnClickListener{
            val url = lien_ram
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
     // bouton qui sert à rediriger l'utilisateur vers l'url de la cg
        photo_cg.setOnClickListener{
            val url = lien_cg
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    // bouton qui sert à rediriger l'utilisateur vers l'url de la cm
        photo_cm.setOnClickListener{
            val url = lien_cm
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

    // bouton qui sert à rediriger l'utilisateur vers l'url de la cm
        photo_ssd.setOnClickListener{
            val url = lien_ssd
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

     // bouton qui sert à rediriger l'utilisateur vers l'url de l'alimentation
        photo_alim.setOnClickListener{
            val url = lien_alim
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

     // bouton qui sert à rediriger l'utilisateur vers l'url du boitier
        photo_boitier.setOnClickListener{
            val url = lien_boitier
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }








    }
    private fun read(child : String, id:TextView){
        database.getReference("Config 800").child(child)
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

    // Lire et afficher le lien du processeur
    private fun readLienProcesseur(){
        database.getReference("Config 800").child("lien_processeur")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.getValue()
                    lien_proc = value.toString()

                }

            })
    }

    // Lire et afficher le lien de la ram
    private fun readLienRam() {
        database.getReference("Config 800").child("lien_ram")
            .addListenerForSingleValueEvent(object : ValueEventListener {
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
        database.getReference("Config 800").child("lien_cg")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value
                    lien_cg = value.toString()
                }

            })
    }

    private fun readLienCM(){
        database.getReference("Config 800").child("lien_cm")
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_cm = value
                }

            })

    }

    private fun readLienSSD(){
        database.getReference("Config 800").child("lien_ssd")
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_ssd = value
                }

            })

    }

    private fun readLienAlim(){
        database.getReference("Config 800").child("lien_alim")
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_alim = value
                }

            })

    }

    private fun readLienBoitier(){
        database.getReference("Config 800").child("lien_boitier")
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_boitier = value
                    progressbar.visibility = GONE
                }

            })

    }
}