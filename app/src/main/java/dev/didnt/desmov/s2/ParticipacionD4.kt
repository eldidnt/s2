package dev.didnt.desmov.s2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class ParticipacionD4 : AppCompatActivity() {
    private lateinit var txtNombre: EditText
    private lateinit var txtSueldoBase: EditText
    private lateinit var txtEdad: EditText
    private lateinit var txtNroHijos: EditText
    private lateinit var btnC: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participacion_d4)
        asignarPreferencias()
    }
    fun asignarPreferencias(){
        txtNombre = findViewById(R.id.txtNombre)
        txtSueldoBase = findViewById(R.id.txtSueldo)
        txtEdad = findViewById(R.id.txtEdad)
        txtNroHijos = findViewById(R.id.txtHijos)
        btnC = findViewById(R.id.button)

        btnC.setOnClickListener {
            calcular()
        }
    }

    fun calcular(){
        val nombre = txtNombre.text.toString()
        val sueldo = txtSueldoBase.text.toString()
        val edad = txtEdad.text.toString()
        val hijos = txtNroHijos.text.toString()

        var valida:Boolean = true

        if(nombre.isEmpty()){
            txtNombre.setError("Nombre es obligatorio")
            valida = false
        }
        if(sueldo.isEmpty()){
            txtSueldoBase.setError("Sueldo Base es obligatorio")
            valida = false
        }
        if(edad.isEmpty()){
            txtEdad.setError("Edad es obligatorio")
            valida = false
        }
        if(hijos.isEmpty()){
            txtNroHijos.setError("Número de hijos es obligatorio")
            valida = false
        }

        if(valida){
            mostrarMensaje(nombre, sueldo.toDouble(), edad.toInt(), hijos.toInt())
        }
    }

    fun mostrarMensaje(nombre:String, sueldo:Double, edad:Int, hijos:Int){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Reporte")
        ventana.setMessage("Nombre: $nombre" +
                "\nSueldo base: $sueldo" +
                "\nBono en %: ${retornarPorcentaje(edad)}" +
                "\nSueldo + Bono: ${sueldoConBono(sueldo, calcularBono(sueldo, edad))}" +
                "\nAguinaldo hijos: ${calcularBonoPorHijos(hijos)}" +
                "\nSueldo a pagar: ${sueldoTotal(sueldo, calcularBono(sueldo, edad), calcularBonoPorHijos(hijos))}")
        ventana.setPositiveButton("Aceptar",null)
        ventana.create().show()
    }

    fun calcularBono(sueldo:Double, edad:Int):Double{
        var bono:Double = 0.0
        if(edad >= 20 && edad <= 30){
            bono = sueldo * 0.2
        }else if(edad >= 31 && edad <= 50){
            bono = sueldo * 0.3
        }else if(edad >= 51){
            bono = sueldo * 0.5
        }
        return bono
    }

    fun retornarPorcentaje(edad:Int):Int{
        var porcentaje:Int = 0
        if(edad >= 20 && edad <= 30){
            porcentaje = 20
        }else if(edad >= 31 && edad <= 50){
            porcentaje = 30
        }else if(edad >= 51){
            porcentaje = 50
        }
        return porcentaje
    }

    fun calcularBonoPorHijos(hijos:Int):Int{
        return hijos * 50
    }

    fun sueldoConBono(sueldo:Double, bono:Double):Double{
        return sueldo + bono
    }
    fun sueldoTotal(sueldo:Double, bono:Double, bonoHijos:Int):Double{
        return sueldo + bono + bonoHijos
    }
}