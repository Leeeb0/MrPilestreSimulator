package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
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

    // Nom pour notre fichier de sauvegarde
    public static final String PREFS_NAME = "BilanPilestrePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilan);

        // 2. Récupérer l'objet joueur FINAL
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

        // 5. Sauvegarder le bilan
        sauvegarderBilan();

        // 6. Configurer le bouton Quitter
        btnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        // --- Énergie ---
        pbEnergie.setProgress(energie);
        if (energie >= 70) {
            tvTexteEnergie.setText("Tu débordes d'énergie, prêt à conquérir le monde !");
        } else if (energie >= 40) {
            tvTexteEnergie.setText("Tu tiens le coup, mais une pause ne ferait pas de mal.");
        } else {
            tvTexteEnergie.setText("Tu es épuisé... pense à te reposer demain.");
        }

        // --- Santé ---
        pbSante.setProgress(sante);
        if (sante >= 70) {
            tvTexteSante.setText("Ta santé est au top, continue comme ça !");
        } else if (sante >= 40) {
            tvTexteSante.setText("Tu es en forme, mais attention aux excès.");
        } else {
            tvTexteSante.setText("Ta santé est fragile, prends soin de toi.");
        }

        // --- Bonheur ---
        pbBonheur.setProgress(bonheur);
        if (bonheur >= 70) {
            tvTexteBonheur.setText("Tu es rayonnant, ta journée t'a comblé !");
        } else if (bonheur >= 40) {
            tvTexteBonheur.setText("Tu es plutôt satisfait, mais il manque un petit quelque chose.");
        } else {
            tvTexteBonheur.setText("Tu sembles stressé... essaie de te faire plaisir demain.");
        }

        // --- Argent ---
        pbArgent.setProgress(argent);
        if (argent >= 70) {
            tvTexteArgent.setText("Ton budget est solide, tu peux te faire plaisir.");
        } else if (argent >= 40) {
            tvTexteArgent.setText("Tu gères bien ton argent, mais reste prudent.");
        } else {
            tvTexteArgent.setText("Attention, ton budget fond comme neige au soleil!");
        }

        // --- Discipline ---
        pbDiscipline.setProgress(discipline);
        if (discipline >= 70) {
            tvTexteDiscipline.setText("Tu es exemplaire, une vraie machine de rigueur!");
        } else if (discipline >= 40) {
            tvTexteDiscipline.setText("Tu es plutôt discipliné, mais tu peux faire mieux.");
        } else {
            tvTexteDiscipline.setText("Tu as du mal à suivre une routine... essaie de te cadrer.");
        }

        // --- Stress --- (Attention, logique inversée)
        pbStress.setProgress(stress);
        if (stress <= 30) {
            tvTexteStress.setText("Tu es détendu, rien ne t'atteint aujourd'hui.");
        } else if (stress <= 69) {
            tvTexteStress.setText("Tu as géré ton stress, mais quelques tensions subsistent.");
        } else {
            tvTexteStress.setText("Tu es sous pression... pense à souffler demain.");
        }

        // --- Compétences ---
        pbCompetences.setProgress(competences);
        if (competences >= 70) {
            tvTexteCompetences.setText("Tu as appris plein de choses aujourd'hui, bravo!");
        } else if (competences >= 40) {
            tvTexteCompetences.setText("Tu as progressé, mais tu peux aller plus loin.");
        } else {
            tvTexteCompetences.setText("Tu n'as pas beaucoup stimulé ton esprit aujourd'hui.");
        }

        // --- Retard --- (Logique spéciale)
        pbRetard.setProgress(retard);
        if (retard == 0) {
            tvTexteRetard.setText("Tu as été ponctuel toute la journée, félicitations !");
        } else if (retard <= 20) {
            tvTexteRetard.setText("Un petit retard, rien de grave.");
        } else {
            tvTexteRetard.setText("Tu accumules du retard... attention à la désorganisation.");
        }
    }

    /**
     * Sauvegarde les 8 stats dans les SharedPreferences
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

        editor.apply(); // Valide la sauvegarde
    }
}