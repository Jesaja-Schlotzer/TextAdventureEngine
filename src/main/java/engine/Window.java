package engine;

import engine.io.KeyListener;
import engine.io.MouseListener;
import engine.scenes.SceneHandler;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import util.Time;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Window {

    private static Window instance;


    private int width, height;
    private String title;
    private long glfwWindow;

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Text Adventure Engine";
    }

    public static Window getInstance() {
        if(instance == null) {
            instance = new Window();
        }
        return instance;
    }

    public void run() {
        System.out.println("LWJGL: " + Version.getVersion());

        init();
        loop();

        // Free memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }


    private void init() {
        // Setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if(!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create the window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if(glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window");
        }

        // Register MouseListener
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);

        // Register KeyListener
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);


        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        // Enable V-Sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }


    private void loop() {
        float beginTime = Time.getTime();
        float endTime;
        float deltaTime = -1.0f;


        int targetFps = 60;
        int targetTime = (int) (1e9 / targetFps);

        long lastFPScheck = 0;
        long currentFPS = 0;
        long totalFrames = 0;

        while (!glfwWindowShouldClose(glfwWindow)) {


            // ----------------

            totalFrames++;
            if(System.nanoTime() > lastFPScheck + 1E9) {
                lastFPScheck = System.nanoTime();
                currentFPS = totalFrames;
                totalFrames = 0;
            }
            long startTime = System.nanoTime();

            // ----------------






            // Poll events
            glfwPollEvents();

            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);


            SceneHandler.getInstance().getCurrentScene().update(deltaTime);

            System.out.println(currentFPS);



            glfwSwapBuffers(glfwWindow);








            // Time
            endTime = Time.getTime();
            deltaTime = endTime - beginTime;
            beginTime = endTime;

            // --------------------

            long timeTaken = System.nanoTime() - startTime;

            if(timeTaken < targetTime && false) {
                try {
                    Thread.sleep((targetTime - timeTaken) / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // ---------------------
        }

    }

}
