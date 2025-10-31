package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class SceneMeteoActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private TextView tvDescriptionMeteo;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML
        setContentView(R.layout.activity_scene_meteo);

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets
        tvDescriptionMeteo = findViewById(R.id.tvDescriptionMeteo);
        btnNext = findViewById(R.id.btnNextSceneMeteo);

        // 4. Logique de l'événement aléatoire
        Random random = new Random();
        boolean ilPleut = random.nextBoolean();

        if (ilPleut) {
            joueur.setSante(joueur.getSante() - 5);
            joueur.setBonheur(joueur.getBonheur() - 5);
            tvDescriptionMeteo.setText("Le ciel se couvre soudainement... et une averse éclate ! Vous n'aviez pas de parapluie.\n(Santé -5, Bonheur -5)");
        } else {
            tvDescriptionMeteo.setText("Le ciel se couvre soudainement... mais le soleil perce finalement les nuages. Ouf !");
        }


        // 5. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 6. PRÉPARER L'ACTIVITÉ SUIVANTE
                Intent intent = new Intent(SceneMeteoActivity.this, SceneTacheImprevueActivity.class);

                // On attache l'objet "joueur" MIS À JOUR
                intent.putExtra("JOUEUR_STATS", joueur);

                // On démarre la scène 8
                startActivity(intent);
            }
        });
    }
}