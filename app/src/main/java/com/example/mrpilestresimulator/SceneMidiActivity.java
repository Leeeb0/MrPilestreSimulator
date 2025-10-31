package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // <-- On utilise des Button
import android.widget.Toast;

public class SceneMidiActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private Button btnSeul, btnAmis, btnFastFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML (ConstraintLayout)
        setContentView(R.layout.activity_scene_midi);

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets
        btnSeul = findViewById(R.id.btnSeul);
        btnAmis = findViewById(R.id.btnAmis);
        btnFastFood = findViewById(R.id.btnFastFood);

        // 4. Définir l'action pour le bouton "Seul"
        btnSeul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Seul)
                joueur.setBonheur(joueur.getBonheur() - 5);
                joueur.setArgent(joueur.getArgent() - 2);

                // 6. Passer à la scène suivante
                passerALaSceneSuivante();
            }
        });

        // 4. Définir l'action pour le bouton "Avec des amis"
        btnAmis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Amis)
                joueur.setBonheur(joueur.getBonheur() + 10);
                joueur.setArgent(joueur.getArgent() - 5);

                // 6. Passer à la scène suivante
                passerALaSceneSuivante();
            }
        });

        // 4. Définir l'action pour le bouton "Fast-food"
        btnFastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Fast-food)
                joueur.setSante(joueur.getSante() - 5);
                joueur.setArgent(joueur.getArgent() - 8);
                joueur.setBonheur(joueur.getBonheur() + 5);

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
        // (Scène 11: Après-midi. Vous créerez "SceneApresMidiActivity.java" ensuite)
        Intent intent = new Intent(SceneMidiActivity.this, SceneApresMidiActivity.class);

        // On attache l'objet "joueur" MIS À JOUR
        intent.putExtra("JOUEUR_STATS", joueur);

        // On démarre la scène 11
        startActivity(intent);
    }
}