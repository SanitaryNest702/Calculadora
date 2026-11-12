# Calculadora MVC (CalcMVC) — Documentación

## 1. Descripción
Calculadora de escritorio en Java (Swing) con el patrón **Modelo–Vista–Controlador**. El usuario escribe un número en `Input`, pulsa **Multiply** o **Sumar**, y el resultado acumulado aparece en `Total`. **Clear** reinicia la calculadora. Los cálculos usan `BigDecimal`.

## 2. Estructura
```
src/
├── modelo/       CalcModel.java
├── vista/        CalcView.java
└── controlador/  CalcController.java
                  CalcMVC.java   (main)
```

## 3. Responsabilidades

| Parte | Clase | Responsabilidad |
|---|---|---|
| Modelo | `CalcModel` | Guarda el total (`m_total`) y hace las operaciones: `multiplyBy`, `addBy`, `reset`, `setValue`, `getValue` |
| Vista | `CalcView` | Ventana con `Input`, `Total` y los botones **Multiply**, **Sumar** y **Clear**. Solo muestra y entrega la entrada: `getUserInput`, `setTotal`, `showError`, `reset`, y los métodos `addMultiplyListener`, `addSumaListener`, `addClearListener` |
| Controlador | `CalcController` | Registra los listeners en la vista. Cada botón tiene su clase interna: `MultiplyListener`, `SumaListener`, `ClearListener`. Lee la entrada, llama al modelo y pide a la vista mostrar el resultado o el error |
| Arranque | `CalcMVC` | Crea modelo, vista y controlador una sola vez y muestra la ventana |

## 4. Flujo de una operación
1. El usuario escribe un número y pulsa **Multiply** o **Sumar**.
2. El listener del controlador pide el texto a la vista: `getUserInput()`.
3. Llama al modelo: `multiplyBy(...)` o `addBy(...)`.
4. Lee `getValue()` y se lo pasa a la vista: `setTotal(...)`.
5. Si el texto no es un número, `BigDecimal` lanza `NumberFormatException` y el controlador pide a la vista `showError("Bad input: ...")`.

**Clear:** `ClearListener` llama a `m_model.reset()` y `m_view.reset()`.

## 5. Manejo del valor inicial (mejora de la suma)
Multiplicar y sumar necesitan valores neutros distintos: **1** para multiplicar y **0** para sumar. Además, la ventana debe mostrar **0** al iniciar y después de Clear.

Se resolvió así:
- `CalcModel.INITIAL_VALUE = "1"`: el total interno arranca en 1, así multiplicar no da 0.
- `CalcModel.INITIAL_SUMA = "0"`: el neutro de la suma.
- `m_primeraOperacion` (boolean): es `true` después de `reset()`. En `addBy`, si es la primera operación, la base de la suma es `INITIAL_SUMA` (0) y no el total interno (1); después pasa a `false`.
- `CalcView.INITIAL_VALUE = "0"`: lo que se muestra en `Total` al iniciar y al limpiar. Es solo visual y no afecta los cálculos.

Con esto, `Clear` + suma da 0 + n, y `Clear` + multiplicación da 1 × n, sin que la pantalla muestre el 1.

## 6. Cómo se agregó la suma
Tres cambios, uno en cada parte del MVC:
1. **Modelo:** el método `addBy(String operand)`, junto con `INITIAL_SUMA` y `m_primeraOperacion`.
2. **Vista:** el botón `m_sumaBtn` ("Sumar"), colocado después de Multiply, y el método `addSumaListener`.
3. **Controlador:** la clase interna `SumaListener` y su registro en el constructor con `view.addSumaListener(new SumaListener())`.

Cualquier operación nueva sigue estos mismos tres pasos.

## 7. Otros cambios sobre el ejemplo original
- `BigInteger` se cambió a `BigDecimal` (acepta decimales).
- `INITIAL_VALUE` del modelo se corrigió de `"0"` a `"1"` para que multiplicar funcione después de Clear.

## 8. Diagrama de clases
![Diagrama de clases](diagrama-clases-actual.png)

## 9. Video
URL: _pega aquí el enlace y verifica que abre sin iniciar sesión_
