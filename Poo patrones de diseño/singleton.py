class RegistroPartida:
    _instancia = None

    def __init__(self):
        self.historial = []

    @classmethod
    def get_instancia(cls):
        if cls._instancia is None:          # ← completa
            cls._instancia = RegistroPartida()     # ← instancia la clase
        return cls._instancia

    def registrar(self, accion: str):
        self.historial.append(accion)             # ← atributo correcto
        print(f"[LOG] {accion}")

    def mostrar_historial(self):
        for i, a in enumerate(self.historial, 1):
            print(f"  {i}. {a}")

# Prueba
reg = RegistroPartida()
reg.registrar("Guerrero ataca a Dragón")
reg.registrar("Mago lanza Bola de Fuego")

reg2 = RegistroPartida()
reg2.mostrar_historial()   # debe mostrar AMBAS acciones
print("¿Mismo registro?", reg is reg2)