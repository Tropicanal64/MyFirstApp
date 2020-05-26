package config.fr

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {

// creation des variables que nous déclarerons plutard
    private lateinit var btnConnect:Button
    private lateinit var btnGoregister:Button
    private lateinit var emailConnect:EditText
    private lateinit var passConnect:EditText
    private var mAuth: FirebaseAuth? = null
    private lateinit var progressbar:ProgressBar
    private lateinit var passforget:Button
    private lateinit var checkBox:CheckBox



// fonction de depart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val sharedPref: SharedPreferences = this.getPreferences(Context.MODE_PRIVATE)
    val isMyValueChecked = sharedPref.getBoolean("checkbox", false)

    checkBox = findViewById(R.id.checkremember)
    checkBox.isChecked = isMyValueChecked
    checkBox.setOnClickListener {
        val editor = sharedPref.edit()
        editor.putBoolean("checkbox", checkBox.isChecked)
        editor.commit()
    }


    // déclaration de la variable passforget/Bouton
        passforget = findViewById(R.id.passforget)

    // déclaration de la variable mAuth, permettant la connection à la bdd d'authentification
        mAuth = FirebaseAuth.getInstance()

    // déclaration de la variable btnconnect/Bouton
        btnConnect = findViewById<Button>(R.id.btn_connect)

    // déclaration de la variable btnGoregister/Bouton
        btnGoregister = findViewById<Button>(R.id.btn_goregister)

    // déclaration de la variable emailConnect/ EditText contenant l'email écrit
        emailConnect = findViewById<EditText>(R.id.email_connect)

    // déclaration de la variable passConnect/EditText contenant le mot de passe écrit
        passConnect = findViewById<EditText>(R.id.pass_connect)

    // déclaration de la variable progressbar/ progressBar qui permet d'affciher un petit
    // chargement lors des récupérations ou créations d'infos
        progressbar = findViewById(R.id.progressbar)

    // on masque la progressBar, on la remontrera quand il y aura des chargements
        progressbar.visibility = GONE

    // Le click sur le bouton btnConnect permet d'activer la méthode Signin qui créer un connecte
    // l'utilisateur avec  les informations rentrées dans emailConnect et passConnect si elles sont dans la BDD
        btnConnect.setOnClickListener{
            Signin(emailConnect.text.toString().trim(), passConnect.text.toString().trim())
        }

    //Le click sur le bouton btnGoregister permet d'activier la méthode createAccount qui créer
    // un compte avec les informations rentrées dans emailConnect et passConnect
        btnGoregister.setOnClickListener{
            createAccount(emailConnect.text.toString().trim(), passConnect.text.toString().trim())
        }

    // le click sur le bouton passforget permet d'activer la méthode resetPassword qui envoie un
    // email de réinitialisation de mot de passe à l'adresse mail indiqué dans ll'EditText emailConnect
        passforget.setOnClickListener{
            resetPassword(emailConnect.text.toString().trim())

        }


    }

    // Fonction onStart qui vérifie au début de l'application si l'utilisateur est déjà connecté,
    // si c'est le cas le champs email serra prérempli sinon il sera vide/ le mot de passe est
    // obligé d'etre rentré
    override fun onStart(){
        super.onStart()
        //on check si l'utilisateur utilise déjà un compte ou s'il est null grace à la fonction
        // updateUI. si l'utilisateur a bien était connecté et que son email est vérifié alors
        // son email sera prérempli.
        val currentUser = mAuth!!.currentUser
        if(updateUI(currentUser) == true){
            if(currentUser!!.isEmailVerified) {
                emailConnect.setText(currentUser.email.toString().trim())
                // si l'utilisateur az checker la checkbox il sera connecté d'office
                if(checkBox.isChecked){
                    startActivity(Intent(this, Menu::class.java))
                    finish()
                }
            }

        }
        else{
            Toast.makeText(baseContext, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show()
        }
        }

    // fonction updateUI qui vérifie si l'utilisateur est valide
    fun updateUI(currentUser: FirebaseUser?):Boolean {
        if(currentUser != null){
            return true
        }
        else{
            Toast.makeText(baseContext, "Vous n'etes pas connecté", Toast.LENGTH_SHORT)
            return false
        }

    }

    // fonction perrmettant la création d'un compte avec un email et un mot de pass, si l'email ou
    // le mot de passe est null, c'est à dire sans texte alors la création dde compte ne se fera pas
    // pareil si l'email ne contient pas de caractère d'email et pareil si le mot de passe ne
    //contient pas au moins 5 caractère.
    fun createAccount(email:String, pass:String){
        if(pass.length < 5 ){
            Toast.makeText(baseContext, "Veuillez choisir un mot de pass plus long!", Toast.LENGTH_SHORT).show()
        }
        if(email.isEmpty()){
            Toast.makeText(baseContext, "Veuillez rentrer un email.", Toast.LENGTH_SHORT).show()

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(baseContext, "Veuillez rentrer un email valide ! ", Toast.LENGTH_LONG).show()

        }
        if(pass.isEmpty()){
            Toast.makeText(baseContext, "Veuillez rentrer un mot de passe", Toast.LENGTH_SHORT).show()

        }else{
            progressbar.visibility = VISIBLE
            mAuth!!.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "Inscription effectuée", Toast.LENGTH_SHORT).show()
                        val user = mAuth!!.currentUser
                        emailverification()
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext, "Inscription annulée.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                    progressbar.visibility = GONE

                    // ...
                }

        }


    }

    // fonction permettant l'identification et la connnection à l'application. Si l'email ou le
    // mot de passe est vide alors un message s'affichera pour le dire à l'utilisateur
    // si l'utilisateur est inscrit et a vérifié son email alors la seconde fenetre de l'application s'ouvre
    fun Signin(email:String, pass:String){
        if(email.isEmpty()){
            Toast.makeText(baseContext, "Veuillez rentrer un email.", Toast.LENGTH_SHORT).show()
            return
        }
        if(pass.isEmpty()){
            Toast.makeText(baseContext, "Veuillez rentrer un mot de passe", Toast.LENGTH_SHORT).show()
            return
        }

        else{
            progressbar.visibility = VISIBLE
            mAuth!!.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "Renseignements valides", Toast.LENGTH_SHORT).show()
                        val user = mAuth!!.currentUser
                        updateUI(user)
                        if(user!!.isEmailVerified){
                            startActivity(Intent(this, Menu::class.java))
                            finish()
                        }
                        else{
                            emailverification()
                            Toast.makeText(baseContext, "Vous n'avez pas vérfié votre adresse email !", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext, "Connection échouée.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                    progressbar.visibility = GONE

                    // ...
                }

        }


    }
    // fonction permettant à l'utilisateur de vérifié son mail avec lequel il s'est inscrit à
    // l'application. S'il ne le vérifie pas la connexion échouera
    fun emailverification(){
        val user = mAuth!!.currentUser

        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Email envoyé", Toast.LENGTH_SHORT).show()
                }
            }

    }
    // fonction permettant d'envoyer un email de reinitialisation de mot de passe à l'utilisateur
    // si l'email entré dans l'EditText emailConnect n'est pas valide cela échouera.
    // si il n'y a pas d'email entré dans l'EditText emailConnect alors un message s'affichera à
    // l'utilisateur pour qu'il le fasse
    private fun resetPassword(email:String){
        if(email.isEmpty()){
            Toast.makeText(baseContext, "Veuillez rentrer votre email", Toast.LENGTH_SHORT).show()
        }
        else{
            progressbar.visibility = VISIBLE
            mAuth!!.sendPasswordResetEmail(email).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    Toast.makeText(baseContext, "Email de réinitialisation envoyé", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(baseContext, "L'envoi a échoué", Toast.LENGTH_SHORT).show()

                }
                progressbar.visibility = GONE
            }
        }
        }
    }
