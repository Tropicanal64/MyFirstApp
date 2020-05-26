package config.fr

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth




class ProfilActivity : AppCompatActivity() {

    // création des variables que nous déclarerons plutard
    private lateinit var mAuth:FirebaseAuth
    private lateinit var emailprint:TextView
    private lateinit var disconnect_btn:Button
    private lateinit var reset_pass:Button
    private lateinit var userdel:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)


        //déclaration de la variable userdel qui correspond au bouton de suppression de compte
        userdel = findViewById(R.id.userdel)

        // déclaration de la variable mAuth, permettant la connection à la bdd d'authentification
        mAuth = FirebaseAuth.getInstance()
        //déclaration de la variable emailUser qui correspond à l'email enregistré sur la bdd de
        // l'utilisateur actuel
        var emailUser = mAuth.currentUser!!.email

        //déclaration de la variable emailprint qui correspond à l'email qui sera affiché
        // dans la page Profil
        emailprint = findViewById(R.id.emailprint)

        // déclaration de la variable disconnect_btn qui correspond au bouton de déconnection
        // dans la page Profil
        disconnect_btn = findViewById(R.id.btn_disconnect)

        // déclaration de la variable reset_pass qui correspond au bouton de réinitialisation
        // de mot de passe dans la page Profil
        reset_pass = findViewById(R.id.reset_password)

        // On affiche l'email de l'utilisateur connecté dans le TextView emailprint
        emailprint.text = emailUser

        // Le click sur disconnect_btn permet de déconnecter l'utilisateur de l'application
        // et de fermer la page profil pour le rendre sur la page de connection(MainActivity)
        disconnect_btn.setOnClickListener{
            mAuth.signOut()
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        // le click sur le bouton userdel permet de supprimer le compte
        userdel.setOnClickListener{
            // creation d'une boite de dialogue
            var alerte = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            alerte.setTitle("Attention !") // on lui donnne un titre
            alerte.setMessage("Voulez vous vraiment supprimer votre compte?") // on lui donne un message
            alerte.setIcon(R.drawable.cadena) // on lui donne une icone
            alerte.setPositiveButton("OUI", DialogInterface.OnClickListener { dialog, id -> deleteaccount() }) // on lui donne un bouton oui qui s'il est cliqué activera la fonction se suppression de compte
            alerte.setNegativeButton("Non", DialogInterface.OnClickListener { dialog, which -> dialog.cancel()  }) // on lui donne un bouton non qui s'il est cliqué fermera l'alerte

            // creation de la boite de dialogue
            val alert = alerte.create() //creation d'une varaible qui instancie la boite de dialogue
            alert.show() // on affiche la boit de dialogue
            }


        //Le click sur reset_pass permet d'activer la fonction resetpassword qui enverra un mail
        // à l'utilisateur pour reinitialiser son mot de passe
        reset_pass.setOnClickListener{
            resetPassword(emailUser.toString())
        }


            //déclaration de la variable emailprint qui correspond à l'email qui sera affiché
            // dans la page Profil
            emailprint = findViewById(R.id.emailprint)

            // déclaration de la variable disconnect_btn qui correspond au bouton de déconnection
            // dans la page Profil
            disconnect_btn = findViewById(R.id.btn_disconnect)

            // déclaration de la variable reset_pass qui correspond au bouton de réinitialisation
            // de mot de passe dans la page Profil
            reset_pass = findViewById(R.id.reset_password)

            // On affiche l'email de l'utilisateur connecté dans le TextView emailprint
            emailprint.text = emailUser

            // Le click sur disconnect_btn permet de déconnecter l'utilisateur de l'application
            // et de fermer la page profil pour le rendre sur la page de connection(MainActivity)
            disconnect_btn.setOnClickListener{
                mAuth.signOut()
                finish()
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            // le click sur le bouton userdel permet de supprimer le compte
            userdel.setOnClickListener{
                // creation d'une boite de dialogue
                var alerte = AlertDialog.Builder(this, R.style.AlertDialogTheme)
                alerte.setTitle("Attention !") // on lui donnne un titre
                alerte.setMessage("Voulez vous vraiment supprimer votre compte?") // on lui donne un message
                alerte.setIcon(R.drawable.cadena) // on lui donne une icone
                alerte.setPositiveButton("OUI", DialogInterface.OnClickListener { dialog, id -> deleteaccount() }) // on lui donne un bouton oui qui s'il est cliqué activera la fonction se suppression de compte
                alerte.setNegativeButton("Non", DialogInterface.OnClickListener { dialog, which -> dialog.cancel()  }) // on lui donne un bouton non qui s'il est cliqué fermera l'alerte

                // creation de la boite de dialogue
                val alert = alerte.create() //creation d'une varaible qui instancie la boite de dialogue
                alert.show() // on affiche la boit de dialogue
                }


            //Le click sur reset_pass permet d'activer la fonction resetpassword qui enverra un mail
            // à l'utilisateur pour reinitialiser son mot de passe
            reset_pass.setOnClickListener{
                resetPassword(emailUser.toString())
            }

    }


    // fonction permettant l'envoi d'un mail de réinitialisation de mot de passe à l'utilisateur connecté
    // l'adresse email à laquelle s'envoit le mail est celle de l'utilisateur connecté
    private fun resetPassword(email:String){

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener{task ->

            if(task.isSuccessful){
                Toast.makeText(baseContext, "Email de réinitialisation envoyé", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(baseContext, "L'envoi a échoué", Toast.LENGTH_SHORT).show()

            }

        }
    }

    // fonction qui permet la supression du compte
    private fun deleteaccount(){
        mAuth.currentUser!!.delete().addOnCompleteListener{task ->
            if(task.isSuccessful){
                Toast.makeText(baseContext, "Le compte a été supprimé", Toast.LENGTH_SHORT).show()
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

    }
}
