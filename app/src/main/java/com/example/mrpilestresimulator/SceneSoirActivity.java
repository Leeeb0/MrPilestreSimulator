package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SceneSoirActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private ImageButton btnJeuxVideo, btnLire, btnSport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML
        setContentView(R.layout.activity_scene_soir);

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets
        btnJeuxVideo = findViewById(R.id.btnJeuxVideo);
        btnLire = findViewById(R.id.btnLire);
        btnSport = findViewById(R.id.btnSport);

        // 4. Définir l'action pour "Jeux vidéo"
        btnJeuxVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu [cite: 9595]
                joueur.setBonheur(joueur.getBonheur() + 5);
                joueur.setEnergie(joueur.getEnergie() - 5);
                passerALaSceneSuivante();
            }
        });

        // 4. Définir l'action pour "Lire"
        btnLire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu
                joueur.setCompetences(joueur.getCompetences() + 5);
                joueur.setEnergie(joueur.getEnergie() - 5);
                passerALaSceneSuivante();
            }
        });

        // 4. Définir l'action pour "Sport"
        btnSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu
                joueur.setSante(joueur.getSante() + 10);
                joueur.setEnergie(joueur.getEnergie() - 10);
                passerALaSceneSuivante();
            }
        });
    }

    private void passerALaSceneSuivante() {
        // 6. PRÉPARER L'ACTIVITÉ SUIVANTE
        Intent intent = new Intent(SceneSoirActivity.this, SceneDinerActivity.class);

        // On attache l'objet "joueur" MIS À JOUR
        intent.putExtra("JOUEUR_STATS", joueur);

        // On démarre la scène 15
        startActivity(intent);
    }
}