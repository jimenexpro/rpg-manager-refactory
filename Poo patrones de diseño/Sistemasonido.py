class SistemaSonido(Observador):
    def actualizar(self, personaje: str, evento: str):
        if "daño" in evento:
            print(f"  [SFX] 💥 sonido_golpe.mp3")
        elif "nivel" in evento:
            print(f"  [SFX] 🎵 level_up.mp3")
        else:
            print(f"  [SFX] 🔔 evento_generico.mp3")

# Suscribir
sfx = SistemaSonido()
personaje.suscribir(sfx)

# Probar que suena
personaje.recibir_daño(25)
personaje.subir_nivel()

# Desuscribir y verificar silencio
personaje.desuscribir(sfx)
personaje.recibir_daño(10)   # SistemaSonido ya no reacciona