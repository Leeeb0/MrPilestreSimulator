package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SceneNotificationActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private ImageButton btnInvitation, btnMauvaiseNouvelle, btnRien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML
        setContentView(R.layout.activity_scene_notification);

        // 2. Récupérer l'objet joueur transmis par SceneHygieneActivity
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");

        // Sécurité pour éviter un crash
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets du layout XML
        btnInvitation = findViewById(R.id.btnInvitation);
        btnMauvaiseNouvelle = findViewById(R.id.btnMauvaiseNouvelle);
        btnRien = findViewById(R.id.btnRien);

        // 4. Définir l'action pour CHAQUE bouton

        // Clic sur "Invitation"
        btnInvitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu
                joueur.setBonheur(joueur.getBonheur() + 5);

                // 6. Passer à la scène suivante
                passerALaSceneSuivante();
            }
        });

        // Clic sur "Mauvaise nouvelle"
        btnMauvaiseNouvelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu
                joueur.setBonheur(joueur.getBonheur() - 5);

                // 6. Passer à la scène suivante
                passerALaSceneSuivante();
            }
        });

        // Clic sur "Rien"
        btnRien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 6. Passer à la scène suivante
                passerALaSceneSuivante();
            }
        });
    }

    private void passerALaSceneSuivante() {
        // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
        Intent intent = new Intent(SceneNotificationActivity.this, ScenePetitDejeunerActivity.class);

        // On attache l'objet "joueur" MIS À JOUR
        intent.putExtra("JOUEUR_STATS", joueur);

        // On démarre la scène 5
        startActivity(intent);
    }
}