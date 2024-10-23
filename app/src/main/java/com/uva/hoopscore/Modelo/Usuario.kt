package com.uva.hoopscore.Modelo

class Usuario {
    var uid : String = ""
    var usuario : String = ""
    var email : String = ""
    var nombre : String = ""
    var fecha_nacimiento : String = ""
    var equipo : String = ""

    constructor()
    constructor(uid: String, usuario: String, email: String, nombre: String, fechaNacimiento: String, equipo: String) {
        this.uid = uid
        this.usuario = usuario
        this.email = email
        this.nombre = nombre
        this.fecha_nacimiento = fechaNacimiento
        this.equipo = equipo
    }


}