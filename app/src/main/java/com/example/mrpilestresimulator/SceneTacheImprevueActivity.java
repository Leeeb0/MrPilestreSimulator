package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // <-- On utilise des Button
import android.widget.Toast;

public class SceneTacheImprevueActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private Button btnAccepter, btnRefuser; // <-- Les deux boutons de choix

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML (ConstraintLayout)
        setContentView(R.layout.activity_scene_tache_imprevue);

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets
        btnAccepter = findViewById(R.id.btnAccepter);
        btnRefuser = findViewById(R.id.btnRefuser);

        // 4. Définir l'action pour le bouton "Accepter"
        btnAccepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Accepter)
                joueur.setCompetences(joueur.getCompetences() + 5);
                joueur.setEnergie(joueur.getEnergie() - 10);

                // 6. Passer à la scène suivante
                passerALaSceneSuivante();
            }
        });

        // 4. Définir l'action pour le bouton "Refuser"
        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Refuser)
                joueur.setBonheur(joueur.getBonheur() + 5);
                joueur.setDiscipline(joueur.getDiscipline() - 5);

                // 6. Passer à la scène suivante
                passerALaSceneSuivante();
            }
        });
    }

    /**
     * Méthode partagée pour lancer l'activité suivante.
     */
    private void passerALaSceneSuivante() {
        // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
        // (Scène 9: Pause détente. Vous créerez "ScenePauseDetenteActivity.java" ensuite)
        Intent intent = new Intent(SceneTacheImprevueActivity.this, ScenePauseDetenteActivity.class);

        // On attache l'objet "joueur" MIS À JOUR
        intent.putExtra("JOUEUR_STATS", joueur);

        // On démarre la scène 9
        startActivity(intent);
    }
}