package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context; // <-- Requis pour SharedPreferences
import android.content.SharedPreferences; // <-- Requis pour la sauvegarde (vu en TP PokeStatSuite)
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BilanActivity extends AppCompatActivity {

    private MrPilestre joueur;

    // Déclaration de TOUS les widgets
    private ProgressBar pbEnergie, pbSante, pbBonheur, pbArgent, pbDiscipline, pbStress, pbCompetences, pbRetard;
    private TextView tvTexteEnergie, tvTexteSante, tvTexteBonheur, tvTexteArgent, tvTexteDiscipline, tvTexteStress, tvTexteCompetences, tvTexteRetard;
    private Button btnQuitter;

    // Nom pour notre fichier de sauvegarde (logique TP PokeStatSuite) [cite: 9992-10004]
    public static final String PREFS_NAME = "BilanPilestrePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML du Bilan
        setContentView(R.layout.activity_bilan);

        // 2. Récupérer l'objet joueur FINAL (logique TP PokeStat) [cite: 11129, 9959]
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre(); // Sécurité
        }

        // 3. Lier TOUS les widgets
        pbEnergie = findViewById(R.id.pbEnergie);
        tvTexteEnergie = findViewById(R.id.tvTexteEnergie);
        pbSante = findViewById(R.id.pbSante);
        tvTexteSante = findViewById(R.id.tvTexteSante);
        pbBonheur = findViewById(R.id.pbBonheur);
        tvTexteBonheur = findViewById(R.id.tvTexteBonheur);
        pbArgent = findViewById(R.id.pbArgent);
        tvTexteArgent = findViewById(R.id.tvTexteArgent);
        pbDiscipline = findViewById(R.id.pbDiscipline);
        tvTexteDiscipline = findViewById(R.id.tvTexteDiscipline);
        pbStress = findViewById(R.id.pbStress);
        tvTexteStress = findViewById(R.id.tvTexteStress);
        pbCompetences = findViewById(R.id.pbCompetences);
        tvTexteCompetences = findViewById(R.id.tvTexteCompetences);
        pbRetard = findViewById(R.id.pbRetard);
        tvTexteRetard = findViewById(R.id.tvTexteRetard);

        btnQuitter = findViewById(R.id.btnQuitter);

        // 4. Afficher le bilan
        afficherBilan();

        // 5. Sauvegarder le bilan (Exigence Projet 5.2)
        sauvegarderBilan();

        // 6. Configurer le bouton Quitter (Exigence Projet 5.1)
        btnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cette commande ferme TOUTES les activités et quitte l'app
                finishAffinity();
            }
        });
    }

    /**
     * Remplit les champs du bilan avec les stats du joueur
     */
    private void afficherBilan() {
        int energie = joueur.getEnergie();
        int sante = joueur.getSante();
        int bonheur = joueur.getBonheur();
        int argent = joueur.getArgent();
        int discipline = joueur.getDiscipline();
        int stress = joueur.getStress();
        int competences = joueur.getCompetences();
        int retard = joueur.getRetard();

        // --- Énergie --- [cite: 9747-9749]
        pbEnergie.setProgress(energie);
        if (energie >= 70) {
            tvTexteEnergie.setText("Tu débordes d'énergie, prêt à conquérir le monde !"); [cite: 9747]
        } else if (energie >= 40) {
            tvTexteEnergie.setText("Tu tiens le coup, mais une pause ne ferait pas de mal."); [cite: 9748]
        } else {
            tvTexteEnergie.setText("Tu es épuisé... pense à te reposer demain."); [cite: 9749]
        }

        // --- Santé --- [cite: 9751-9753]
        pbSante.setProgress(sante);
        if (sante >= 70) {
            tvTexteSante.setText("Ta santé est au top, continue comme ça !"); [cite: 9751]
        } else if (sante >= 40) {
            tvTexteSante.setText("Tu es en forme, mais attention aux excès."); [cite: 9752]
        } else {
            tvTexteSante.setText("Ta santé est fragile, prends soin de toi."); [cite: 9753]
        }

        // --- Bonheur --- [cite: 9755-9757]
        pbBonheur.setProgress(bonheur);
        if (bonheur >= 70) {
            tvTexteBonheur.setText("Tu es rayonnant, ta journée t'a comblé !"); [cite: 9755]
        } else if (bonheur >= 40) {
            tvTexteBonheur.setText("Tu es plutôt satisfait, mais il manque un petit quelque chose."); [cite: 9756]
        } else {
            tvTexteBonheur.setText("Tu sembles stressé... essaie de te faire plaisir demain."); [cite: 9757]
        }

        // --- Argent --- [cite: 9759-9761]
        pbArgent.setProgress(argent);
        if (argent >= 70) {
            tvTexteArgent.setText("Ton budget est solide, tu peux te faire plaisir."); [cite: 9759]
        } else if (argent >= 40) {
            tvTexteArgent.setText("Tu gères bien ton argent, mais reste prudent."); [cite: 9760]
        } else {
            tvTexteArgent.setText("Attention, ton budget fond comme neige au soleil!"); [cite: 9761]
        }

        // --- Discipline --- [cite: 9763-9764]
        pbDiscipline.setProgress(discipline);
        if (discipline >= 70) {
            tvTexteDiscipline.setText("Tu es exemplaire, une vraie machine de rigueur!"); [cite: 9763]
        } else if (discipline >= 40) {
            tvTexteDiscipline.setText("Tu es plutôt discipliné, mais tu peux faire mieux."); [cite: 9764]
        } else {
            tvTexteDiscipline.setText("Tu as du mal à suivre une routine... essaie de te cadrer."); [cite: 9764]
        }

        // --- Stress --- (Attention, logique inversée) [cite: 9766-9769]
        pbStress.setProgress(stress);
        if (stress <= 30) {
            tvTexteStress.setText("Tu es détendu, rien ne t'atteint aujourd'hui."); [cite: 9766]
        } else if (stress <= 69) {
            tvTexteStress.setText("Tu as géré ton stress, mais quelques tensions subsistent."); [cite: 9767-9768]
        } else {
            tvTexteStress.setText("Tu es sous pression... pense à souffler demain."); [cite: 9769]
        }

        // --- Compétences --- [cite: 9771-9773]
        pbCompetences.setProgress(competences);
        if (competences >= 70) {
            tvTexteCompetences.setText("Tu as appris plein de choses aujourd'hui, bravo!"); [cite: 9771]
        } else if (competences >= 40) {
            tvTexteCompetences.setText("Tu as progressé, mais tu peux aller plus loin."); [cite: 9772]
        } else {
            tvTexteCompetences.setText("Tu n'as pas beaucoup stimulé ton esprit aujourd'hui."); [cite: 9773]
        }

        // --- Retard --- (Logique spéciale) [cite: 9775-9777]
        pbRetard.setProgress(retard); // On peut ajuster le 'max' de cette barre si 100 est trop
        if (retard == 0) {
            tvTexteRetard.setText("Tu as été ponctuel toute la journée, félicitations !"); [cite: 9775]
        } else if (retard <= 20) {
            tvTexteRetard.setText("Un petit retard, rien de grave."); [cite: 9776]
        } else {
            tvTexteRetard.setText("Tu accumules du retard... attention à la désorganisation."); [cite: 9777]
        }
    }

    /**
     * Sauvegarde les 8 stats dans les SharedPreferences (logique TP PokeStatSuite) [cite: 9992-10004]
     */
    private void sauvegarderBilan() {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("energie", joueur.getEnergie());
        editor.putInt("sante", joueur.getSante());
        editor.putInt("bonheur", joueur.getBonheur());
        editor.putInt("argent", joueur.getArgent());
        editor.putInt("discipline", joueur.getDiscipline());
        editor.putInt("stress", joueur.getStress());
        editor.putInt("competences", joueur.getCompetences());
        editor.putInt("retard", joueur.getRetard());

        editor.apply(); // Valide la sauvegarde [cite: 10003, 10430, 11404]
    }
}