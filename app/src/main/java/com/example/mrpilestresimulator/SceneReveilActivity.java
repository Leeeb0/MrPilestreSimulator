package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SceneReveilActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private RadioGroup wakeUpGroup;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_reveil);

        // 2. RÉCUPÉRER L'OBJET JOUEUR
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets du layout XML
        wakeUpGroup = findViewById(R.id.wakeUpGroup);
        btnNext = findViewById(R.id.btnNextSceneReveil);

        // 4. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = wakeUpGroup.getCheckedRadioButtonId();

                // 5. Appliquer la logique du jeu
                if (selectedId == R.id.rbSnooze) {
                    joueur.setEnergie(joueur.getEnergie() + 5);
                    joueur.setRetard(joueur.getRetard() + 1);
                } else if (selectedId == R.id.rbGetUp) {
                    joueur.setEnergie(joueur.getEnergie() - 5);
                    joueur.setDiscipline(joueur.getDiscipline() + 1);
                } else {
                    // 6. Contrôle d'erreur
                    Toast.makeText(SceneReveilActivity.this, "Fais un choix !", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
                Intent intent = new Intent(SceneReveilActivity.this, SceneHygieneActivity.class);
                intent.putExtra("JOUEUR_STATS", joueur);
                startActivity(intent);
            }
        });
    }
}