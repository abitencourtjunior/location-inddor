package br.com.location.indoor.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class CommandService {

    /**
     * @param command - Executa um comando simples sem permissão administrador
     *
     * @author abitencourt
     */
    public String executeSimple(String command) throws Exception {
        StringBuilder result = new StringBuilder();
        Process process = Runtime.getRuntime().exec(command);

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            result.append(line).append("\n");
        }

        process.waitFor();
        return result.toString();
    }

    /**
     * @param command - Executa um comando simples com permissão administrador
     *
     * @author abitencourt
     */
    public String executeWithRoot() throws Exception {
        String[] cmd = { "/bin/bash", "-c", "echo 280617| sudo -S iwlist wlp2s0 scan" };
        StringBuilder result = new StringBuilder();
        Process process = Runtime.getRuntime().exec(cmd);

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            result.append(line).append("\n");
        }

        process.waitFor();
        return result.toString();
    }

}
