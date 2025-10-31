package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SceneTransportActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private RadioGroup transportGroup;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML (ConstraintLayout)
        setContentView(R.layout.activity_scene_transport);

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets
        transportGroup = findViewById(R.id.transportGroup);
        btnNext = findViewById(R.id.btnNextSceneTransport);

        // 4. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = transportGroup.getCheckedRadioButtonId();

                // 5. Appliquer la logique du jeu (selon votre plan "android (3).pdf")
                if (selectedId == R.id.rbPied) { // Option: À pied
                    joueur.setSante(joueur.getSante() + 5);
                    joueur.setEnergie(joueur.getEnergie() - 5);
                } else if (selectedId == R.id.rbBus) { // Option: Bus
                    joueur.setArgent(joueur.getArgent() - 2);
                } else if (selectedId == R.id.rbVoiture) { // Option: Voiture
                    joueur.setArgent(joueur.getArgent() - 10);
                    joueur.setStress(joueur.getStress() + 2);
                } else {
                    // 6. Contrôle d'erreur (requis par le projet)
                    Toast.makeText(SceneTransportActivity.this, "Comment comptes-tu y aller ?", Toast.LENGTH_SHORT).show();
                    return; // On arrête le clic ici
                }

                // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
                // (Scène 7: Météo. Vous créerez "SceneMeteoActivity.java" ensuite)
                Intent intent = new Intent(SceneTransportActivity.this, SceneMeteoActivity.class);

                // On attache l'objet "joueur" MIS À JOUR
                intent.putExtra("JOUEUR_STATS", joueur);

                // On démarre la scène 7
                startActivity(intent);
            }
        });
    }
}