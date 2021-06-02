 package nelson.aparecido.estudaki;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.jaeger.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity {

    private ImageView materias, aulas, notas, playlist,playlist_historia;
    private View calendario, lupa, home, professor, perfil, btn_me_ajuda;
    private TextView txtNome;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setTransparent(this);

        txtNome = findViewById(R.id.txt_nome_principal);
        materias = findViewById(R.id.img_materias_telaprincipal);
        aulas = findViewById(R.id.img_aulas_telaprincipal);
        notas = findViewById(R.id.img_notas_telaprincipal);
        playlist = findViewById(R.id.playlist_telainicial);
        playlist_historia = findViewById(R.id.playlist_filmes_telainicial);

        //Ir para tela Me Ajuda
        btn_me_ajuda = (View) findViewById(R.id.view_me_ajuda_main);
        btn_me_ajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MeAjudaActivity.class);
                startActivity(intent);
            }
        });


        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoURL("https://youtube.com/playlist?list=PLVkT5_GzN_AL0Vuaz1PTK-wuWTU6AIGKL");


            }
        });

        playlist_historia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoURL("https://youtube.com/playlist?list=PLVkT5_GzN_ALcVpcTap1r8AK4VfJCLmED");


            }
        });

        materias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MateriasActivity.class);
                startActivity(intent);


            }
        });

        aulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AulasActivity.class);
                startActivity(intent);


            }
        });

//        notas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getApplicationContext(), NotasActivity.class);
//                startActivity(intent);
//
//
//            }
//        });

            /* BARRA DE TAREFA */
            calendario = findViewById(R.id.view_calendario);
            lupa = findViewById(R.id.view_lupa);
            home = findViewById(R.id.view_home);
            professor = findViewById(R.id.view_conversa_professor);
            perfil = findViewById(R.id.view_perfil);


           perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        professor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BatePapoActivity.class);
                startActivity(intent);
            }
        });

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CalendarioActivity.class);
                startActivity(intent);
            }
        });

        lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PesquisaActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuario").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot documentSnapshotMain, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if(documentSnapshotMain != null){
                    txtNome.setText(documentSnapshotMain.getString("nome"));
                }
            }
        });
    }

    private void gotoURL(String s) {

        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}