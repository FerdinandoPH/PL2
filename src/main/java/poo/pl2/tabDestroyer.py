import os
for archivo in os.listdir("C:\\Users\\perez\\Documents\\OneDrive - Universidad de Alcala\\0_UNI\\0_programas\\PROG\\PL2\\src\\main\\java\\poo\\pl2"):
    if archivo.endswith(".java"):
        with open(archivo, "r") as f:
            lineas = f.readlines()
        with open(archivo, "w") as g:
            for linea in lineas:
                linea = linea.replace("\t", "    ")
                g.write(linea)
