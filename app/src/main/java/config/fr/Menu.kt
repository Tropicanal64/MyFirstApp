package config.fr

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_config_menu.*
import kotlin.properties.Delegates


class Menu : AppCompatActivity() {

    // creation des variables

    private var database = Firebase.database
    private lateinit var btn_profil: ImageButton
    private lateinit var btnconfig: ConstraintLayout
    private lateinit var btnperiph: ConstraintLayout

    private lateinit var infobtn: ImageButton

    private lateinit var switch:Switch

    private lateinit var twitter_btn:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_menu)

        // on défini la vairable switch permettant de switcher entre le mode nocturne et le mode jour
        switch = findViewById(R.id.switch_btn)

        // on créer un module permettant l'enregistrement du switch pour qu'au démarrage
        // de l'appli le switch soit tel qu'on l'a laissé

        val sharedPreferences = this.getSharedPreferences("switch", Context.MODE_PRIVATE)
            val defaultSwitch = sharedPreferences.getBoolean("switch", false)

            switch.isChecked = defaultSwitch
            switch.setOnClickListener{
                var editor = sharedPreferences.edit()
                editor.putBoolean("switch", switch.isChecked)
                editor.commit()
                onStart()


            }



    // module permettant d'affiché un messsage à la premiere exécution de l'appli, expliquant le
        // fonctionnement des boutons
        var numberpref = this.getSharedPreferences("number", Context.MODE_PRIVATE)
        var defaultnumber = numberpref.getBoolean("number", true)
        var numberforinfoapp = defaultnumber
        var editornumber = numberpref.edit()

        if (numberforinfoapp == true){
            var infoapp = AlertDialog.Builder(this, R.style.AlertInfosTheme)
            infoapp.setTitle("Comment accéder aux produits ?")
            infoapp.setMessage("Pour accéder aux produits présentés il vous suffit de cliquer sur les images , c'est aussi simple que ça !")
            infoapp.setNeutralButton("Ne plus afficher", DialogInterface.OnClickListener { dialog, which -> editornumber.putBoolean("number", false).commit() })
            var alerteinfoapp = infoapp.create()
            alerteinfoapp.show()
        }


// on défini les variables
        btn_profil = findViewById(R.id.btn_profil)
        btnconfig = findViewById(R.id.lesconfigs)
        btnperiph = findViewById(R.id.lesperiphs)

        infobtn = findViewById(R.id.info_btn)

        twitter_btn = findViewById(R.id.twitter_btn)

        btnconfig.setOnClickListener {
            startActivity(Intent(this, ConfigMain::class.java))
        }

        btnperiph.setOnClickListener {
            startActivity(Intent(this, PeriphMain::class.java))
        }

        btn_profil.setOnClickListener {
            startActivity(Intent(this, ProfilActivity::class.java))
        }

        // si le bouton info est clické alors on ouvre une alertdialog
        infobtn.setOnClickListener {
            var alerte = AlertDialog.Builder(this, R.style.AlertInfosTheme)
            alerte.setIcon(R.drawable.logo_info)
            alerte.setTitle("Informations")
            alerte.setMessage("Pour informations les configurations proposées sur l'application sont mises à jour toutes les semaines")
            alerte.setNeutralButton(
                "Ok, pas de soucis",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel()})

            val alert = alerte.create()
            alert.show()
        }

        // si le bouton twitter est clické on ouvre le twitter de l'appli
        twitter_btn.setOnClickListener{
            var url = "https://twitter.com/ConfigFacile"
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        readMaj()

    }


    // fonction onStart qui vérifie le switch et gere le mode en  conséquence
    override fun onStart() {
        super.onStart()

        if (switch.isChecked){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else if(!switch.isChecked){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }



    }

    // fonction qui lis dans la bdd si une mise à jour est dispo

    private fun readMaj(){
        database.getReference("maj").child("majdispo3")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    var majdispo = value

                    if (majdispo == "true"){
                        var lien_playstore = "https://play.google.com/store/apps/details?id=config.fr"
                        var lienappli = Intent(Intent.ACTION_VIEW, Uri.parse(lien_playstore))
                        var alertemaj = AlertDialog.Builder(this@Menu, R.style.AlertInfosTheme)
                        alertemaj.setIcon(R.drawable.logo_info)
                        alertemaj.setMessage("Nouvelle version disponible !")
                        alertemaj.setTitle("MAJ")
                        alertemaj.setPositiveButton("D'accord", DialogInterface.OnClickListener { dialog, which -> startActivity(lienappli)  })
                        alertemaj.setNegativeButton("Plus tard", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
                        var alertemajdispo = alertemaj.create()
                        alertemajdispo.show()
                    }



                }

            })
    }


}
